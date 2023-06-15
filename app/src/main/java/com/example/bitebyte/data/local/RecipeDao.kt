package com.example.bitebyte.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bitebyte.data.model.Recipe

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavorite(recipe: Recipe)

    @Query("DELETE FROM recipe WHERE recipe.id = :id")
    fun removeFavorite(id: Int)

    @Query("SELECT * FROM recipe ORDER BY name ASC")
    fun getAllRecipe(): LiveData<List<Recipe>>
}
