package com.example.recipemanagerc490.Fragments
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipemanagerc490.SavedCustomRecipe
import com.example.recipemanagerc490.RecipeActivity
import com.example.recipemanagerc490.Database.DB
import com.example.recipemanagerc490.Database.RecipeList
import com.example.recipemanagerc490.R
import kotlinx.android.synthetic.main.saved_custom_recipe.view.*
import kotlinx.android.synthetic.main.saved_custom_recipe_fragment.view.*


class AddedRecipeFragment : androidx.fragment.app.Fragment() {


    private var savedCustomRecipe: SavedCustomRecipe? = null
    private var layoutManager: androidx.recyclerview.widget.RecyclerView.LayoutManager? = null
    private var recipeList: ArrayList<RecipeList>? = null


    override fun onCreateView(
        //inflate fragment below
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var defaultView = inflater.inflate(R.layout.saved_custom_recipe_fragment, container, false)


        recipeList = ArrayList()
        layoutManager = androidx.recyclerview.widget.LinearLayoutManager(defaultView.context)
        savedCustomRecipe = SavedCustomRecipe(recipeList!!, defaultView.context)
        defaultView.savedCustomRecipeList.adapter = savedCustomRecipe
        defaultView.savedCustomRecipeList.layoutManager = layoutManager

        //query recipe list once app opened
        loadRecipeList("%")
        savedCustomRecipe!!.notifyDataSetChanged()

        // click on the chicken thing to add new recipe
        defaultView.saveRecipeButton.setOnClickListener {

            var intent = Intent(defaultView.context, RecipeActivity::class.java)
            startActivity(intent)

        }
        return defaultView
    }

    //once app reopened then call loadRecipeList and query all the previously saved infos
    override fun onResume() {
        super.onResume()
        loadRecipeList("%")
        // Notifies the attached observers that the underlying data has been changed and any View reflecting the data set should refresh itself.
        savedCustomRecipe!!.notifyDataSetChanged()
    }

    //function used to query all existing recipes that were entered or stored and show the title as header
    private fun loadRecipeList(title:String){
        var dbManager= DB(this.context!!)
        val recipeQueryList= arrayOf("Id","Title", "Ingredients")
        val selectionArgs= arrayOf(title)
        val cursor=dbManager.dbQuery(recipeQueryList,"Id like ?",selectionArgs)
        recipeList!!.clear()

        //Move the cursor to the first row.

        if(cursor.moveToFirst()){
            //Query all the columns in a table into one long text view and/or string
            // get the first column and display all of its contents
            do{

                val recipeID=cursor.getInt(cursor.getColumnIndex("Id"))
                val recipeTitle=cursor.getString(cursor.getColumnIndex("Title"))
                val recipeIngredients=cursor.getString(cursor.getColumnIndex("Ingredients"))

                recipeList!!.add(RecipeList(recipeID, recipeTitle, recipeIngredients))

            }while (cursor.moveToNext())
        }
    }
}
