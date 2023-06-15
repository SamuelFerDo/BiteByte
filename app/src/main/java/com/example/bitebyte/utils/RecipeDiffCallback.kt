package com.example.bitebyte.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.bitebyte.data.model.Recipe

class RecipeDiffCallback  (private val mOldList: List<Recipe>, private val mNewList: List<Recipe>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldList.size
    }

    override fun getNewListSize(): Int {
        return mNewList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].name == mNewList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = mOldList[oldItemPosition]
        val newUser = mNewList[newItemPosition]
        return oldUser.description == newUser.description && oldUser.calories == newUser.calories
    }
}