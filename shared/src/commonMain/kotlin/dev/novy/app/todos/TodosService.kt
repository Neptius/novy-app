package dev.novy.app.todos

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class TodosService(
    private val httpClient: HttpClient
) {
    private val country = "us"
    private val category = "business"
    private val apiKey = "249913a89a0a4d759aeb076211be1a47"

    suspend fun fetchTodos(): List<TodoRaw> {
        val url = "https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey"
        val response: TodosResponse =
            httpClient
                .get(url)
                .body()
        println(url)

        return response.todos
    }
}