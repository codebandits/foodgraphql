package io.pivotal.rsanchez.foodgraphql.dataStorage

import io.pivotal.rsanchez.foodgraphql.data.FoodNutrient
import io.pivotal.rsanchez.foodgraphql.data.Nutrient
import io.pivotal.rsanchez.foodgraphql.data.UnknownNutrient
import io.pivotal.rsanchez.foodgraphql.dataStorage.response.NutrientResponse
import org.springframework.stereotype.Component

@Component
class NutrientRepository(
        private val listOfNutrients: List<Nutrient>
) {
    fun getNutrientInfo(nutrient: NutrientResponse): Nutrient =
        listOfNutrients.find { it.id == nutrient.id }?.let {
            FoodNutrient(
                    id = nutrient.id,
                    value = nutrient.value,
                    name = it.name,
                    typeTag = it.typeTag,
                    unit = it.unit,
                    notes = it.notes

            )
        } ?: UnknownNutrient()
}