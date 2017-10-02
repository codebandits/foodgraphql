package io.pivotal.rsanchez.foodgraphql.resolvers

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.pgutkowski.kgraphql.schema.dsl.SchemaBuilder
import io.pivotal.rsanchez.foodgraphql.ApiConfigurator
import io.pivotal.rsanchez.foodgraphql.data.Nutrient
import io.pivotal.rsanchez.foodgraphql.dataStorage.ApiAggregator
import io.pivotal.rsanchez.foodgraphql.dataStorage.NutrientRepository
import io.pivotal.rsanchez.foodgraphql.data.Food
import io.pivotal.rsanchez.foodgraphql.dataStorage.response.NutritionixResponse
import org.springframework.stereotype.Component

@Component
class FoodResolver(
        val nutritionixAPI: ApiAggregator,
        val nutrientRepository: NutrientRepository,
        val objectMapper: ObjectMapper
) : ApiConfigurator {
    override val config: SchemaBuilder<Unit>.() -> Unit = {
        query("food") {
            resolver { name: String ->
                val request = nutritionixAPI.getByFieldName("query", name)
                objectMapper.readValue(request, NutritionixResponse::class.java).foods.first()
            }
        }

        query("foods") {
            resolver { name: String ->
                val request = nutritionixAPI.getByFieldName("query", name)
                objectMapper.readValue(request, NutritionixResponse::class.java).foods
            }
        }

        type<Food> {
            property<Collection<Nutrient>>(name = "nutrients") {
                resolver {
                    it.nutrients.map { nutrientRepository.getNutrientInfo(it) } }
            }
        }
    }
}