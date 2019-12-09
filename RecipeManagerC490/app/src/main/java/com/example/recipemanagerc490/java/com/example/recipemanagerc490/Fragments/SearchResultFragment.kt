package com.example.recipemanagerc490.Fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipemanagerc490.Database.RecipeSearch
import com.example.recipemanagerc490.R
import com.example.recipemanagerc490.ShowSearchedResult
import kotlinx.android.synthetic.main.searched_result_list_fragment.view.*

//fragment for result after search
    class SearchResultFragment : androidx.fragment.app.Fragment()  {
    private var searchResultBridge: ShowSearchedResult? = null
    private var recyclerLayout: androidx.recyclerview.widget.RecyclerView.LayoutManager? = null

        //inflate the below fragments
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val defaultView = inflater.inflate(R.layout.searched_result_list_fragment, container, false)

        val bundle = arguments
            // get title and thumbnail from search
        val recipeIdentifiers = bundle!!.getSerializable("results") as RecipeSearch

            recyclerLayout = androidx.recyclerview.widget.LinearLayoutManager(defaultView.context)
            //identifiers being the title and thumbnail as set in RecipeSearch
            searchResultBridge = ShowSearchedResult(recipeIdentifiers, defaultView.context)

        defaultView.reclyclerSearchrecipe.adapter = searchResultBridge
        defaultView.reclyclerSearchrecipe.layoutManager = recyclerLayout

        return defaultView
    }
}
