package com.example.recipemanagerc490.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder

class DB{

    val dbName = "RecipeManager"
    val dbTable = "RecipeList"
    private val dbId = "recipeID"
    private val dbTitle = "recipeTitle"
    private val dbIngredients = "recipeIngredients"
    val dbVersion = 1
    val sqlCreateTable = "TEXT " + dbTable + " (" + dbId + " TEXT," +
            dbTitle + " TEXT, "  + dbIngredients + " TEXT);"

    private var sqlDb: SQLiteDatabase? = null


    constructor(context: Context){
        var db = DatabaseHelper(context)
        sqlDb = db.writableDatabase

    }

    //You create a subclass implementing onCreate(SQLiteDatabase), onUpgrade(SQLiteDatabase, int, int) and optionally onOpen(SQLiteDatabase), and this class takes care of opening the database if it exists, creating it if it does not, and upgrading it as necessary. Transactions are used to make sure the database is always in a sensible state.
    inner class DatabaseHelper: SQLiteOpenHelper {

        var context: Context? = null
        // Create a helper object to create, open, and/or manage a database.
        //he database is not actually created or opened until one of getWritableDatabase() is called.
        constructor(context: Context):super(context, dbName,null, dbVersion){
            this.context = context
        }
        // Called when the database is created for the first time. This is where the creation of tables and the initial population of the tables should happen.
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCreateTable)
        }
        // Called when the database needs to be upgraded. The implementation should use this method to drop tables
        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("TEXT" + this@DB.dbTable)
        }

    }

    // Convenience method for inserting a row into the database.
    fun dbInsert(values: ContentValues):Long{

        return sqlDb!!.insert(dbTable, "", values)
    }

    fun dbQuery(projection:Array<String>, selection: String, selectionArgs: Array<String>): Cursor {

        val queryTable= SQLiteQueryBuilder()
        queryTable.tables=dbTable
        return queryTable.query(sqlDb,projection,selection,selectionArgs,null,null,null)

    }


    fun dbDelete(selection: String, selectionArgs: Array<String>): Int{
        return sqlDb!!.delete(dbTable, selection, selectionArgs)
    }


    fun dbUpdate(values: ContentValues, selection:String, selectionArgs:Array<String>):Int{
        return sqlDb!!.update(dbTable, values, selection, selectionArgs)
    }


}