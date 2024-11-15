package com.example.recyclerviewproject

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel() : ViewModel() {

    private val data = Data()

    private val shuffledTitles = arrayRandomize(data.titles)
    private val shuffledDetails = arrayRandomize(data.details)
    private val shuffledImages = arrayRandomize(data.images)

    /*
    private val shuffledTitles = data.titles.toList().shuffled()
    private val shuffledDetails = data.details.toList().shuffled()
    private val shuffledImages = data.images.toList().shuffled()
    */

    private fun arrayRandomize(oldArray: Array<String>): Array<String> {
        return Array(oldArray.size) {
            oldArray[Random.nextInt(oldArray.size)]
        }
    }

    private fun arrayRandomize(oldArray: IntArray): Array<Int> {
        return Array(oldArray.size) {
            oldArray[Random.nextInt(oldArray.size)]
        }
    }

    public fun getTitle (position: Int): String {
        return shuffledTitles[position]
    }
    public fun getDetails (position: Int): String {
        return shuffledDetails[position]
    }
    public fun getImage (position: Int): Int {
        return shuffledImages[position]
    }

}