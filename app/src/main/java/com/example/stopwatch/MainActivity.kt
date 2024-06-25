package com.example.stopwatch

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var countdowntimer: CountDownTimer
    private lateinit var textView: TextView
    private var START_TIME_IN_MILES: Long = 120000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textview)
        countdowntimer = object : CountDownTimer(START_TIME_IN_MILES, 1) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(minutes)
                val milliseconds = (millisUntilFinished % 1000) / 10
                val timeRemaining = String.format("%02d:%02d:%02d", minutes, seconds, milliseconds)
                textView.text = timeRemaining
            }

            override fun onFinish() {
                textView.text = "Time's Up"
            }
        }

        val startButton = findViewById<Button>(R.id.start)
        startButton.setOnClickListener {
            countdowntimer.start()
        }

        val stopButton = findViewById<Button>(R.id.stop)
        stopButton.setOnClickListener {
            countdowntimer.cancel()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}