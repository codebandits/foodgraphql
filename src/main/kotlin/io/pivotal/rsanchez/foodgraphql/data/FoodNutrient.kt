package io.pivotal.rsanchez.foodgraphql.data

data class FoodNutrient(
        override val id: Long,
        override val typeTag: String,
        override val name: String,
        override val unit: UNITS,
        override val notes: String?,
        override val value: Double? = 0.0
) : Nutrient

