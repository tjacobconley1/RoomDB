package com.example.roomdb.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    // reads all data from the database
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    // function used to add a new user
    // to the databse
    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}