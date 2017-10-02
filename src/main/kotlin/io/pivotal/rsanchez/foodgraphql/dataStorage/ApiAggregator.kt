package io.pivotal.rsanchez.foodgraphql.dataStorage

interface ApiAggregator {
    fun getByFieldName(fieldName: String, query: String): String
}