package io.pivotal.rsanchez.foodgraphql.data

interface Nutrient {
    val id: Long
    val value: Double?
    val typeTag: String
    val name: String
    val unit: UNITS
    val notes: String?
}