package com.example.recipemanagerc490.Database
import java.io.Serializable

// identifiers used for custom list to pass objects to activities
data class RecipeList(
    var id: Int,
    var title: String,
    var ingredients: String
    ): Serializable