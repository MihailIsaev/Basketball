package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second2.*
import java.util.*

class SecondActivity2 : AppCompatActivity() {
    companion object {
        const val TOTAL_COUNT = "total_count"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second2)
        showRandomNumber()
    }
    fun showRandomNumber() {

        val count = intent.getIntExtra(TOTAL_COUNT, 0)

        val random = Random()

        var randomInt = 0

        if (count > 0) {
            randomInt = random.nextInt(count + 1)
        }

        textViewRandom.text = Integer.toString(randomInt)

        textViewLabel.text = getString(R.string.random_heading, count)

    }
}