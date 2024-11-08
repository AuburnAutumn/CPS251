package com.example.recyclerviewwithintent

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Loads data that was sent through the intent
        val title = intent.getStringExtra("title")
        val detail = intent.getStringExtra("detail")
        val imageResId = intent.getIntExtra("imageResId", 0)

        //And displays it
        findViewById<TextView>(R.id.titleDisplay).text = title
        findViewById<TextView>(R.id.descriptionDisplay).text = detail
        findViewById<ImageView>(R.id.imageView).setImageResource(imageResId)

    }
}