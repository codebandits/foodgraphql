package io.pivotal.rsanchez.foodgraphql.dataStorage

import com.fasterxml.jackson.databind.ObjectMapper
import io.pivotal.rsanchez.foodgraphql.data.Nutrient
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class NutritionixAPI(
        @Value("\${fetchUrl}") private  val fetchUrl: String,
        @Value("\${nutritionix.app.id}") private val apiId: String,
        @Value("\${nutritionix.app.key}") private val apiKey: String,
        private val restTemplate: RestTemplate,
        private val objectMapper: ObjectMapper
): ApiAggregator {

    override fun getByFieldName(fieldName: String, query: String): String {
        val httpHeaders = HttpHeaders()
        val requestBody = mapOf( fieldName to query)
        httpHeaders.set("x-app-id", apiId)
        httpHeaders.set("x-app-key", apiKey)
        httpHeaders.set("Content-Type", "application/json")
        val jsonBody = objectMapper.writeValueAsString(requestBody)
        val httpEntity = HttpEntity(jsonBody, httpHeaders)
        return restTemplate.exchange(
                fetchUrl,
                HttpMethod.POST,
                httpEntity,
                String::class.java).body
    }
}

