package io.pivotal.rsanchez.foodgraphql.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import io.pivotal.rsanchez.foodgraphql.dataStorage.response.NutrientResponse

@JsonIgnoreProperties(ignoreUnknown = true)
data class Food(
        @JsonProperty("food_name") val name: String,
        @JsonProperty("brand_name") val brandName: String?,
        @JsonProperty("serving_qty") val servingQuantity: Int,
        @JsonProperty("full_nutrients") val nutrients: List<NutrientResponse>
)