package com.example.bitebyte.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitebyte.data.model.ShoppingList

@Dao
interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertShoppingList(ingredients: ShoppingList)

    @Query("DELETE FROM shopping_list WHERE shopping_list.id = :id")
    fun removeShoppingList(id: Int)

    @Query("DELETE FROM shopping_list")
    fun removeAllShoppingList()

    @Query("SELECT * FROM shopping_list ORDER BY name ASC")
    fun getAllShoppingList(): LiveData<List<ShoppingList>>
}
