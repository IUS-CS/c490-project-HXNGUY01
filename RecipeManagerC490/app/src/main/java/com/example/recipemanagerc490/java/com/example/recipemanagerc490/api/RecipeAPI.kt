package com.example.recipemanagerc490.api
import com.example.recipemanagerc490.Database.RecipeSearch
import retrofit2.http.GET
import retrofit2.http.Query


interface RecipeAPI {

    @GET("/api/")

    suspend fun fetchAPI(@Query("q") token: String): RecipeSearch

}