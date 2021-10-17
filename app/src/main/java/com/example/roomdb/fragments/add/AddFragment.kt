package com.example.roomdb.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdb.R
import com.example.roomdb.data.User
import com.example.roomdb.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class addFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        // create the viewmodel
        // this is a necessary layer in order to
        // allow the UI and the Back end interact
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // button to execute the insert to DB
        view.add_btn.setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    // pulls the data from the Edit Text boxes
    // and inserts it into the DB
    private fun insertDataToDatabase() {
        val firstName = addFirstName_et.text.toString()
        val lastName = addLastName_et.text.toString()
        val age = addAge_et.text

        if(inputCheck(firstName, lastName, age)){
            // create User Object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            // Add Data to Databse
            mUserViewModel.addUser(user)
            // display a Toast to the user so
            // that they know it was entered successfully
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // navigate back to previous fragment
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            // Display a Toast if any nulls were returned
            Toast.makeText(requireContext(), "Please fill out all fields. ", Toast.LENGTH_LONG).show()
        }
    }

    // returns True if fields are not null
    // returns false if they are null
    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}