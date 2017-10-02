package io.pivotal.rsanchez.foodgraphql

import com.github.pgutkowski.kgraphql.KGraphQL
import com.github.pgutkowski.kgraphql.schema.Schema
import io.pivotal.rsanchez.foodgraphql.data.FoodNutrient
import io.pivotal.rsanchez.foodgraphql.data.UNITS
import io.pivotal.rsanchez.foodgraphql.data.UnknownNutrient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate

@Configuration
class SchemaConfiguration(val configs: List<ApiConfigurator>) {
    @Bean
    fun foodSchema() : Schema<*> = KGraphQL.schema {
        configure {
            useDefaultPrettyPrinter = true
        }

        stringScalar<LocalDate> {
            serialize = { it.toString() }
            deserialize = { LocalDate.parse(it) }
        }

        configs.forEach { it.config(this) }

        type<FoodNutrient>()
        type<UnknownNutrient>()
        enum<UNITS>()
    }
}