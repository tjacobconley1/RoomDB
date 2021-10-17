package com.example.roomdb.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// this class represents an entity within
// the roomdb
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
)
