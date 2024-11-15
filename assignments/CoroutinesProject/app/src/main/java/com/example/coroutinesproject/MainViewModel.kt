package com.example.coroutinesproject

import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {

    //Array stored in ViewModel so it can be rotated
    val list: MutableList<String> = ArrayList()


    public fun updateNameList(newName : String){
        list.add(newName)
    }

    public fun getNameCount(): Int {
        return list.size
    }

}