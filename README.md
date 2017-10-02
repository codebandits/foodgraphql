(work in progress)

This is an experiment to learn and understand the development of GraphQL application, and yes this means I have not written many tests.

## Example of a Spring Boot application using [KGraphQL](https://github.com/pgutkowski/KGraphQL)

This is an example of a Spring Boot application that returns a [GraphQL](http://facebook.github.io/graphql/October2016/) endpoint at `/graphql`.
Let me explain you the reasons why you might want to use this language instead of REST.

Some patterns I have seen building APIs involve poor designed internal APIS,
different requirements for each client using the same API, and you might need to aggregate different services only to serve this centralized new API.
If you encounter these patterns I would suggest you to use GraphQL over REST.

Let's use [Nutritionix](https://developer.nutritionix.com/) API as an example. This API has two ways to collect data that the user might need. 
By a POST method, and using csv files for part of the data. We will abstract this API in a graphQL endpoint. 

For this example, we will use two main domain objects that this API provides. Food and its nutrients.
Food information is exposed using an REST endpoint in their servers. On the other hand, nutrient's information is saved in a csv file.

GraphQL gives you out the box that different type of client use the same API with custom responses. Let's say we have two clients. 
The first client wants to use this API to know about food and their brands, and the second client wants to use it to know the nutrients of foods.

You have two options with REST. You create two contracts/endpoints for each type of clients, or you serve all information to both. 
GraphQL gives you a query language that helps to build the response depending of what the user ask. For more information, please read the GraphQL specs.
 
One of the benefits of GraphQL is that it gives you a query language that helps the consumer to ask what it needs and the provider to fetch only what it was asked for.
When we defined our resolver for the main object. The `nutrientRepository` is called only when the client asks for nutrients information. 
Otherwise it is smart/lazy enough to never evaluate this lambda. 

```kotlin
type<Food> {
    property<Collection<Nutrient>>(name = "nutrients") {
        resolver {
            it.nutrients.map { nutrientRepository.getNutrientInfo(it) } 
        }
    }
}
```

In this case, our client that only needs food and its brand will never have to wait for the nutrients external API call.


I will use this example to extend and promote [KGraphQL](https://github.com/pgutkowski/KGraphQL) development.