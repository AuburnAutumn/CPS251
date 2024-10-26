package com.example.namerotation

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val nameList: MutableList<String> = ArrayList()
    private var output: String = "No names to display"

    //Adds new name to the array
    fun addName(newName : String) {
            nameList.add(newName)
            output = "" //Clears output to prevent repeated names
        for (name in nameList) { //Loop to build output string
            output += name + "\n"
        }
    }

    fun noNameError(errorMessage : String){
        output = errorMessage
    }

    fun getOutput(): String {
        return output
    }
}