package com.sks.mathgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var result: TextView
    private lateinit var playAgainButton: Button
    private lateinit var quitGameButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        result = findViewById(R.id.textViewResult)
        playAgainButton = findViewById(R.id.buttonPlayAgain)
        quitGameButton = findViewById(R.id.buttonQuitGame)

        val score = intent.getIntExtra("score", 0)
        result.text = "You have scored: " + score.toString()

        playAgainButton.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        quitGameButton.setOnClickListener {
            //standard code to close the application
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}