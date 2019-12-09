package com.example.recipemanagerc490

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.example.recipemanagerc490.Fragments.AddedRecipeFragment
import com.example.recipemanagerc490.Fragments.SearchPageFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //move navigationMenu bar to bottom
    private val selectionEvent = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigationRecipeList -> {
                showAddedRecipe()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigationSearchRecipe -> {
                showSearchedRecipe()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    //create activity main and ensure navigationMenu bar goes to bottom
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationBar.setOnNavigationItemSelectedListener(selectionEvent)
    }

    // fragment for list view of added recipes
    private fun showAddedRecipe() {
        val manager = supportFragmentManager
        manager.findFragmentById(R.id.fragment)
        manager.beginTransaction()
            .attach(AddedRecipeFragment())
            .replace(R.id.fragment, AddedRecipeFragment()).commit()

    }
    //fragment for the search page
    private fun showSearchedRecipe() {
        val manager = supportFragmentManager
        manager.findFragmentById(R.id.fragment)
        manager.beginTransaction()
            .attach(SearchPageFragment())
            .replace(R.id.fragment, SearchPageFragment()).commit()
    }
}
