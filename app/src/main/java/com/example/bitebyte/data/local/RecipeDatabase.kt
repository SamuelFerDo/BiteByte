package com.example.bitebyte.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bitebyte.data.model.Recipe
import com.example.bitebyte.data.model.ShoppingList
import com.example.bitebyte.utils.ListConverter

@Database(entities = [Recipe::class, ShoppingList::class], version = 3, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun shoppingListDao(): ShoppingListDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getDatabase(context: Context): RecipeDatabase {
            if (INSTANCE == null) {
                synchronized(RecipeDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RecipeDatabase::class.java,
                        "recipe_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as RecipeDatabase
        }
    }
}