package com.example.recipemanagerc490.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RecipePuppyRepository {
    //api here
    private val baseUrl = "http://www.recipepuppy.com"
    private var retrofitAPI: Retrofit? = null

    init {


        // After you’ve added the converter to Retrofit, it’ll automatically take care of mapping the JSON data to your Java objects.
        // This works for both directions: sending and receiving data.
        retrofitAPI = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun getPuppyAPI(): RecipeAPI{
        return retrofitAPI!!.create<RecipeAPI>(RecipeAPI::class.java)
    }
}