package com.example.recipemanagerc490.Fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipemanagerc490.Database.RecipeSearch
import com.example.recipemanagerc490.R
import com.example.recipemanagerc490.api.RecipePuppyRepository
import kotlinx.android.synthetic.main.recipe_search_fragment.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// fragment for just the search page right before search used
class SearchPageFragment : androidx.fragment.app.Fragment() {

    private val recipeService = RecipePuppyRepository()

    override fun onCreateView(
        // inflate below fragments
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val defaultView = inflater.inflate(R.layout.recipe_search_fragment, container, false)
        //if user pressed enter then api usage is initialized
        defaultView.searchRecipeText.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //async background process once api usage is launched
                GlobalScope.launch(Dispatchers.Main) {
                    var searchOutputRecipe = recipeService.getPuppyAPI().fetchAPI(
                        //convert user's input to string inside the blank field
                        defaultView.searchRecipeText.text.toString()
                    )
                    //show foods search result for whatever user entered
                    showSearches(searchOutputRecipe, defaultView.searchRecipeText.text.toString())
                }
            }
            false
        }

        return defaultView
    }

    private fun showSearches(recipe: RecipeSearch, userInput: String) {
        val manager = activity!!.supportFragmentManager
        manager.findFragmentById(R.id.fragment)
        var transaction = manager.beginTransaction()
        var searchResults = SearchResultFragment()
        //put title and thumbnail to recipe and return in search
        val bundle = Bundle()
        bundle.putSerializable("results", recipe)
        bundle.putString("Food Search", userInput)
        searchResults.arguments = bundle

        transaction.attach(SearchResultFragment())
        transaction.replace(R.id.fragment, searchResults).commit()

    }
}
