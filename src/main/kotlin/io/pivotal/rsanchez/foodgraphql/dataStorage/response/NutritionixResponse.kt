package io.pivotal.rsanchez.foodgraphql.dataStorage.response

import io.pivotal.rsanchez.foodgraphql.data.Food

data class NutritionixResponse(
        val foods: List<Food>
)