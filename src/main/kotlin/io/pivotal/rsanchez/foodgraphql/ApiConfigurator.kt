package io.pivotal.rsanchez.foodgraphql

import com.github.pgutkowski.kgraphql.schema.dsl.SchemaBuilder

interface ApiConfigurator {
    val config: SchemaBuilder<Unit>.() -> Unit
}