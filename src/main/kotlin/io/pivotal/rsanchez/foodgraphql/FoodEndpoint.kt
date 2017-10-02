package io.pivotal.rsanchez.foodgraphql

import com.github.pgutkowski.kgraphql.schema.Schema
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class FoodEndpoint(val foodSchema: Schema<Any>) {

    @RequestMapping(value = "/graphql", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseBody
    fun execute(@RequestParam query: String): String {
        return foodSchema.execute(query)
    }
}

