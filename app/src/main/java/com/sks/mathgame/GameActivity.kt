package com.sks.mathgame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var textViewScore: TextView
    private lateinit var textViewLife: TextView
    private lateinit var textViewTime: TextView

    private lateinit var textViewQuestion: TextView
    private lateinit var editTextAnswer: EditText

    private lateinit var buttonOk: Button
    private lateinit var buttonNext: Button

    private var correctAnswer = 0
    private var userScore = 0
    private var userLife = 3

    private lateinit var timer: CountDownTimer
    private val startTimerInMillis: Long = 30000
    private var timeLeftInMillis: Long = startTimerInMillis

    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        category = intent.getStringExtra("category")
        val actionbar = intent.getStringExtra("actionbar")

        supportActionBar!!.title = actionbar.toString()

        textViewScore = findViewById(R.id.textViewScore)
        textViewLife = findViewById(R.id.textViewLife)
        textViewTime = findViewById(R.id.textViewTime)
        textViewQuestion = findViewById(R.id.textViewQuestion)
        editTextAnswer = findViewById(R.id.editTextAnswer)
        buttonOk = findViewById(R.id.buttonOk)
        buttonNext = findViewById(R.id.buttonNext)

        gameContinue()

        buttonOk.setOnClickListener {

            val input = editTextAnswer.text.toString()

            if (input == "") {
                Toast.makeText(applicationContext, "Please enter your answer", Toast.LENGTH_LONG)
                    .show()
            } else {
                pauseTimer()
                resetTimer()

                val userAnswer = input.toInt()

                if (userLife >= 0) {
                    if (userAnswer == correctAnswer) {
                        correctAnswer = 0
                        userScore += 10
                        textViewScore.text = userScore.toString()
                        gameContinue()
                    } else if (userLife == 0) {
                        Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@GameActivity, ResultActivity::class.java)
                        intent.putExtra("score", userScore)
                        startActivity(intent)
                        finish()
                    } else {
                        correctAnswer = 0
                        userLife--
                        textViewLife.text = userLife.toString()
                        gameContinue()
                    }
                } else {
                    Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@GameActivity, ResultActivity::class.java)
                    intent.putExtra("score", userScore)
                    startActivity(intent)
                    finish()
                }
            }
        }

        buttonNext.setOnClickListener {
            pauseTimer()
            resetTimer()

            editTextAnswer.setText("")

            if (userLife <= 0) {
                Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity, ResultActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()
            } else {
                gameContinue()
            }
        }
    }

    private fun gameContinue() {
        editTextAnswer.setText("")

        if (category == "add") {
            val number1 = Random.nextInt(1, 100)
            val number2 = Random.nextInt(1, 100)
            textViewQuestion.text = "$number1 + $number2"
            correctAnswer = number1 + number2
        } else if (category == "sub") {
            val number1 = Random.nextInt(1, 100)
            val number2 = Random.nextInt(1, 100)
            textViewQuestion.text = "$number1 - $number2"
            if (number1 < number2) {
                correctAnswer = number2 - number1
            } else {
                correctAnswer = number1 - number2
            }
        } else {
            val number1 = Random.nextInt(1, 20)
            val number2 = Random.nextInt(1, 20)
            textViewQuestion.text = "$number1 * $number2"
            correctAnswer = number1 * number2
        }
        startTimer()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {

            //what action needs to be done when timer ticks
            override fun onTick(millisUntilFinished: Long) {
                //this method will work until 'timeLeftInMillis=60secs'
                timeLeftInMillis = millisUntilFinished
                updateTimeText()
            }

            //what action needs to be done when time finishes
            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateTimeText()

                userLife--
                textViewLife.text = userLife.toString()
                textViewQuestion.text = "Sorry, Time is up!"
            }

        }.start()
    }

    private fun updateTimeText() {
        val remainingTime: Int = (timeLeftInMillis / 1000).toInt()
        textViewTime.text = remainingTime.toString()
    }

    private fun pauseTimer() {
        timer.cancel()
    }

    private fun resetTimer() {
        timeLeftInMillis = startTimerInMillis
        updateTimeText()
    }
}