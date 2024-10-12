package com.example.tipcalculatorangelabonk

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculatorangelabonk.databinding.ActivityMainBinding
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onButtonClick(view: View) {
        var output = "" //I could use to vals instead but this is what I'm used to from Java haha
        val bill = binding.editTextNumberDecimal.text.toString().toDoubleOrNull()

            if (bill == null){
                output = "YOU MUST ENTER A BILL AMOUNT"
                binding.textView.text = output
            }
            else {
                output = "The tips are as follows: \n\n" +
                "10% = " + String.format(Locale.US,"%.2f", (bill * 1.1)) + "\n" +
                "15% = " + String.format(Locale.US,"%.2f", (bill * 1.15)) + "\n" +
                "20% = " + String.format(Locale.US,"%.2f", (bill * 1.2))
                    //Tips can also be calculated as (bill + (bill*0.1) but this looks cleaner in my opinion
                binding.textView.text = output
            }
        }
    }

