package io.pivotal.rsanchez.foodgraphql.dataStorage.response

import com.fasterxml.jackson.annotation.JsonProperty

data class NutrientResponse(
        @JsonProperty("attr_id") val id: Long,
        @JsonProperty("value") val value: Double
)