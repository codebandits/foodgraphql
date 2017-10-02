package io.pivotal.rsanchez.foodgraphql

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class FoodGraphQLApplication

fun main(args: Array<String>) {
    SpringApplication.run(FoodGraphQLApplication::class.java, *args)
}
