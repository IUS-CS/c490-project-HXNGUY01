package com.example.recipemanagerc490
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.example.recipemanagerc490.Database.RecipeSearch
import kotlinx.android.synthetic.main.searched_result_fragment.view.*



class ShowSearchedResult(
    //initialize values and recycle view
    private val searchRecipe: RecipeSearch,
    private val context: Context
) : RecyclerView.Adapter<ShowSearchedResult.ViewHolder>() {

    //inflater for search result
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.searched_result_fragment, parent, false)
        return ViewHolder(view)
    }
    //bind view and make sure that if search query returns an item with no image then use the default image
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchRecipe.results[position]
        holder.searchedTitle.text = item.title


        if (item.thumbnail != "") Picasso.get().load(item.thumbnail).into(holder.searchedThumbnail)


    }
    //Returns the total number of items in the data set held by the adapter.
    override fun getItemCount(): Int = searchRecipe.results.size

    //view holder for title and thumbnail
    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val searchedTitle: TextView = mView.title
        // view holder for default image
        val searchedThumbnail: ImageView = mView.default_image


    }
}