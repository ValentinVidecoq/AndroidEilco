package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            val result = rollDice(6, R.id.imageViewDice1) + rollDice(6, R.id.imageViewDice2)
            val resultTextView: TextView = findViewById(R.id.textViewResult)
            resultTextView.text = ("Result : $result")
        }
    }

    /**
     * Roll the dice and update the image with the result.
     */
    private fun rollDice(numSides: Int, imageViewId: Int): Int {
        // Create new Dice object with 6 sides and roll it
        val dice = Dice(numSides)
        val diceRoll = dice.roll()

        // Update the screen with the dice roll
        val diceImageView: ImageView = findViewById(imageViewId)

        /**
         * Tried to make a little animation of rolling dices but the images won't update
         */
        /*for (i in 0..10){
            diceUpdate((1..6).random(), diceImageView)
            android.os.SystemClock.sleep(100)
        }*/
        diceUpdate(diceRoll, diceImageView)
        return diceRoll
    }

    /**
     * Update the dice image based on roll result
     */
    private fun diceUpdate(diceRoll: Int, diceImageView :ImageView){
        when(diceRoll){
            1 -> diceImageView.setImageResource(R.drawable.dice_1)
            2 -> diceImageView.setImageResource(R.drawable.dice_2)
            3 -> diceImageView.setImageResource(R.drawable.dice_3)
            4 -> diceImageView.setImageResource(R.drawable.dice_4)
            5 -> diceImageView.setImageResource(R.drawable.dice_5)
            6 -> diceImageView.setImageResource(R.drawable.dice_6)
        }
    }
}

class Dice(private val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}