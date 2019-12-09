package com.example.recipemanagerc490

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipemanagerc490.Database.DB
import kotlinx.android.synthetic.main.add_custom_recipe_screen.*
import kotlinx.android.synthetic.main.add_custom_recipe_fields.*

class RecipeActivity : AppCompatActivity() {

    var emptyRecipe = 0
    //create add recipe fields

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_custom_recipe_screen)
        // when there's nothing in the field allows set recipe name and ingredients

        try{
            var bundle:Bundle= intent.extras
            emptyRecipe=bundle.getInt("Id",0)
            titleInput.setText(bundle.getString("Title") )
            ingredientsInput.setText(bundle.getString("Ingredients"))
        }catch (ex:Exception){}
        // on click allows edit recipe and its ingredients

        floatingChickenButton.setOnClickListener { addRecipe(this, titleInput.text.toString(),
            ingredientsInput.text.toString())}
    }

    // calling some database function and if title and ingredients are empty then allow inputs to be inserted to the empty fields

    private fun addRecipe(context: Context, title:String, ingredients: String ){
        if (title != "" && ingredients != "") {

            var dbManager = DB(context)

            var values = ContentValues()
            values.put("Title", title)
            values.put("Ingredients", ingredients)

            if (emptyRecipe == 0) {
                 dbManager.dbInsert(values)

            } else {
                //if nothing entered then leave as is

                var selectionArgs = arrayOf(emptyRecipe.toString())
                dbManager.dbUpdate(values, "Id=?", selectionArgs)

            }
            // on press go back to menu

            onBackPressed()

        }
    }
}