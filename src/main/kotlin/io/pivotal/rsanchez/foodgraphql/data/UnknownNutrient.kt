package io.pivotal.rsanchez.foodgraphql.data

data class UnknownNutrient(
        override val id: Long = 0,
        override val value: Double = 0.0,
        override val typeTag: String = "",
        override val name: String = "unknown",
        override val unit: UNITS = UNITS.UNKNOWN,
        override val notes: String? = ""

) : Nutrient