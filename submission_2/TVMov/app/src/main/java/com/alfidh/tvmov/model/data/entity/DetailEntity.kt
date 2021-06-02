package com.alfidh.tvmov.model.data.entity

data class DetailEntity(
    val id: Int,
    val title: String,
    val date: String,
    val pic: String,
    val rate: Double,
    val genres: List<String>,
    val desc: String
)