package com.example.roomdb.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


// This interface is used to interact with
// the room DB,
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    // this function is accessed by the
    // UserRepository Class
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    // this function is accessed by the
    // UserRepository Class
    fun readAllData(): LiveData<List<User>>
}