package com.example.recipemanagerc490
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.recipemanagerc490.Database.DB
import com.example.recipemanagerc490.Database.RecipeList
import kotlinx.android.synthetic.main.searched_result_fragment.view.*

class SavedCustomRecipe(
    private val createRecipe: ArrayList<RecipeList>,
    private val context: Context
) : RecyclerView.Adapter<SavedCustomRecipe.ViewHolder>(){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = createRecipe[position]
        //text of item
        holder.recipeTitle.text = item.title
        //click to go to recipe
        holder.itemView.setOnClickListener { putRecipe(item) }
        //delete functions
        holder.itemView.setOnLongClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("DELETE")
            builder.setPositiveButton("YES"){ _, _ ->
                var dbManager = DB(this.context)
                val selectionArgs= arrayOf(item.id.toString())
                dbManager.dbDelete("Id=?", selectionArgs )
                createRecipe.removeAt(position)
                notifyItemRemoved(position)

            }
            builder.setNegativeButton("NO"){ _, _ ->
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
            true
        }
    }
    //inflate recipe list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(context)
            .inflate(R.layout.recipe_text_field, parent, false)
        return ViewHolder(view)
    }

    //Returns the total number of items in the data set held by the adapter.
    override fun getItemCount(): Int = createRecipe.size

    //put recipe into list
    private fun putRecipe(recipeValues: RecipeList){
        var intent = Intent(context, RecipeActivity::class.java)
        intent.putExtra("Id", recipeValues.id)
        intent.putExtra("Title", recipeValues.title)
        intent.putExtra("Ingredients", recipeValues.ingredients)
        ContextCompat.startActivity(context, intent, null)
    }
    //custom viewholder to hold the view of recipe's titles
    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val recipeTitle: TextView = mView.title
    }
}