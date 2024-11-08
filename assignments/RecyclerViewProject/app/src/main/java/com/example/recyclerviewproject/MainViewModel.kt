package com.example.recyclerviewproject

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel() : ViewModel() {

    private val data = Data()

    private val shuffledTitles = arrayRandomize(data.titles)
    private val shuffledDetails = arrayRandomize(data.details)
    private val shuffledImages = arrayRandomize(data.images)

    /*create new arrays of ints, calculate size by the .size of the old arrays and repeat
    picking random numbers. Then have the files look at them as the indexes of the old arrays.
    Pass those numbers around.
    He's also talking about 2d arrays a lot fyi, like a 2d array that lists the arrays and then the
    new numbers
    See if the MainViewModel is accessable from the other Activity honestly
    Make life easier for me

    The way he's talking makes me think I'll be able to access shuffled arrays, good
     */

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