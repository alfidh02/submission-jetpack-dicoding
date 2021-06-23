package com.submissionalfi3.tvmov.model.data.local.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "table_movie")
data class MovieEntity (
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
) : Parcelable