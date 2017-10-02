package io.pivotal.rsanchez.foodgraphql

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.pivotal.rsanchez.foodgraphql.data.FoodNutrient
import io.pivotal.rsanchez.foodgraphql.data.Nutrient
import io.pivotal.rsanchez.foodgraphql.data.UNITS
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.web.client.RestTemplate

@Configuration
class ApiConfiguration {
    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().registerKotlinModule()
    }

    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()

    @Bean
    fun listOfNutrients(): List<Nutrient> {
        val csvMapper = CsvMapper()
        val nutrientsInfoFile = ClassPathResource("nutrients.csv").file
        val schemaFor = CsvSchema.emptySchema().withHeader()
        return csvMapper
                .readerFor(Map::class.java)
                .with(schemaFor)
                .readValues<Map<String, String>>(nutrientsInfoFile)
                .readAll()
                .map {
                    FoodNutrient(
                            id = it["id"]?.toLong() ?: 0,
                            name = it["name"] ?: "",
                            typeTag = it["typeTag"] ?: "",
                            unit = UNITS.valueOf(it["unit"] ?: "UNKNOWN"),
                            notes = it["notes"]
                            )
                }
    }
}