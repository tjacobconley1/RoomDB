package com.example.roomdb.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// the view model provides data to the
// UI and also survive configuration changes
// Communicator between DB and UI
class UserViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository


    // THE INIT BLOCK IS ALWAYS EXECUTED
    // BEFORE ANYTHING ELSE
    init{
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    // this is an operation on the RoomDB
    // never should be executed on the
    // Main Thread
    fun addUser(user: User){
        // this launches everything inside of the
        // brackets as a coroutine
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

}