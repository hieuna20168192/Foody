package com.example.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe(
        @PrimaryKey
        val id: String,
        val title: String,
        val description: String,
        @ColumnInfo(name = "image_url")
        val imageUrl: String,
        @ColumnInfo(name = "number_of_likes")
        val numberOfLikes: Int,
        @ColumnInfo(name = "time_for_ready")
        val timeForReady: Long,
        @ColumnInfo(name = "is_vegan")
        val isVegan: Boolean,
        @ColumnInfo(name = "is_favorite")
        val isFavorite: Boolean,
)
