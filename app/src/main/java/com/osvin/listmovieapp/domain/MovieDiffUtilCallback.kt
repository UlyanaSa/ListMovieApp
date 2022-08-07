package com.osvin.listmovieapp.domain

import androidx.recyclerview.widget.DiffUtil
import com.osvin.listmovieapp.entity.NewMovie

class MovieDiffUtilCallback(private val oldList: List<NewMovie>, private val newList: List<NewMovie>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].title == newList[newItemPosition].title -> true
            oldList[oldItemPosition].onChecked == newList[newItemPosition].onChecked -> true
            else -> false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]== newList[newItemPosition]
    }

}