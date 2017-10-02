package io.pivotal.rsanchez.foodgraphql.dataStorage

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

class NutritionixAPITest {

    val objectMapper = ObjectMapper().registerKotlinModule()
    val fetchUrl = "example.com"
    val apiKey = "app-key"
    val apiId = "app-id"
    val restTemplate = mock<RestTemplate>()
    lateinit var controller: NutritionixAPI

    @BeforeEach
    internal fun setUp() {
        whenever(restTemplate.exchange(
                any<String>(),
                eq(HttpMethod.POST),
                any<HttpEntity<String>>(),
                eq(String::class.java))
        ).thenReturn(ResponseEntity.ok("returned!"))

        controller = NutritionixAPI(
                fetchUrl = fetchUrl,
                apiKey = apiKey,
                apiId = apiId,
                objectMapper = objectMapper,
                restTemplate = restTemplate
        )
    }

    @Test
    fun `builds the request with the right attributes for Nutritionix API`() {
        // Documentation of Nutritionix API https://developer.nutritionix.com/docs/v2
        val expectedHeader = HttpHeaders()
        expectedHeader.set("x-app-id", apiId)
        expectedHeader.set("x-app-key", apiKey)
        expectedHeader.set("Content-Type", "application/json")

        val expectedBody = objectMapper.writeValueAsString(mapOf("aField" to "rip"))

        controller.getByFieldName(fieldName = "aField", query = "rip")

        verify(restTemplate).exchange(
                fetchUrl,
                HttpMethod.POST,
                HttpEntity(expectedBody, expectedHeader),
                String::class.java)
    }

    @Test
    fun `returns the rest template body response`() {
        whenever(restTemplate.exchange(
                any<String>(),
                eq(HttpMethod.POST),
                any<HttpEntity<String>>(),
                eq(String::class.java))
        ).thenReturn(ResponseEntity.ok("a valid response body!"))

        assertThat(controller.getByFieldName(fieldName = "", query = "")).isEqualTo("a valid response body!")
    }
}