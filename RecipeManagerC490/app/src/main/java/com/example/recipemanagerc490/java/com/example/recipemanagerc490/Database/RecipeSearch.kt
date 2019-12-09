package com.example.recipemanagerc490.Database
import java.io.Serializable
//title and thumbnail are json identifier from recipe puppy api and store it inside a list
data class RecipeSearch(
    var title: String,
    var thumbnail: String,
    var results: List<RecipeSearch>

    ): Serializable