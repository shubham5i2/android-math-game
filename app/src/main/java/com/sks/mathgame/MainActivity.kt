package com.sks.mathgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var additionButton: Button
    private lateinit var subtractionButton: Button
    private lateinit var multiplicationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        additionButton = findViewById(R.id.additionButton)
        subtractionButton = findViewById(R.id.subtractionButton)
        multiplicationButton = findViewById(R.id.multiplicationButton)

        additionButton.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("category", "add")
            intent.putExtra("actionbar", "Addition")
            startActivity(intent)
        }

        subtractionButton.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("category", "sub")
            intent.putExtra("actionbar", "Subtraction")
            startActivity(intent)
        }

        multiplicationButton.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("category", "mul")
            intent.putExtra("actionbar", "Multiplication")
            startActivity(intent)
        }
    }
}