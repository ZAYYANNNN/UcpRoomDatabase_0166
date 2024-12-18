package com.example.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matkul")
data class Matkul(
    @PrimaryKey
    val kdMK: String,
    val namaMK: String,
    val sks: String,
    val smstr: String,
    val jenis: String,
    val dospem: String

)
