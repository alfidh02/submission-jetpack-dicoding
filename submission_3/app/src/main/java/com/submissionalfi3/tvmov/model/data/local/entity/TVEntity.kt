package com.submissionalfi3.tvmov.model.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_tv")
data class TVEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "rate")
    val rate: Double,

    @ColumnInfo(name = "desc")
    val desc: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)