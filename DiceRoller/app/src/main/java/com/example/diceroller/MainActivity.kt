package com.example.diceroller

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows the user to roll a dice and see the result
 * on the screen.
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {

            // Get the number of faces of the dice 1 and validate it
            val editTextDice1: EditText = findViewById(R.id.editTextDice1)
            var dice1Faces = 6
            try {
                dice1Faces = editTextDice1.text.toString().trim().toInt()
            }catch (e: NumberFormatException){
                dice1Faces = 6
                Toast.makeText(applicationContext, "Dice 1 input is empty, value set to 6", Toast.LENGTH_SHORT).show()
                editTextDice1.setText("6")
            }
            dice1Faces = validateDiceFaces(dice1Faces, R.id.editTextDice1)

            // Get the number of faces of the dice 2 and validate it
            val editTextDice2: EditText = findViewById(R.id.editTextDice2)
            var dice2Faces = 6
            // Try to parse the editText string and return if exception
            try {
                dice2Faces = editTextDice2.text.toString().trim().toInt()
            }catch (e: NumberFormatException){
                dice2Faces = 6
                Toast.makeText(applicationContext, "Dice 2 input is empty, value set to 6", Toast.LENGTH_SHORT).show()
                editTextDice2.setText("6")
            }
            dice2Faces = validateDiceFaces(dice2Faces, R.id.editTextDice2)

            // Update the view with the results
            val result = rollDice(dice1Faces, R.id.imageViewDice1) + rollDice(dice2Faces, R.id.imageViewDice2)
            val resultTextView: TextView = findViewById(R.id.textViewResult)
            val resultString: String = getString(R.string.textViewResult)
            resultTextView.text = ("$resultString : $result")
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

    /**
     * Check if the value is valid (between 1 and 6)
     * If not valid : Return a valid number and notify user through toast
     */
    private fun validateDiceFaces(numFaces: Int, editText: Int): Int{

        if (numFaces < 1){
            val editTextDice1: EditText = findViewById(editText)
            Toast.makeText(applicationContext, "A dice number is wrong, value set to 1", Toast.LENGTH_SHORT).show()
            editTextDice1.setText("1")
            return 1
        } else if (numFaces > 6) {
            val editTextDice: EditText = findViewById(editText)
            Toast.makeText(applicationContext, "A dice number is wrong, value set to 6", Toast.LENGTH_SHORT).show()
            editTextDice.setText("6")
            return 6
        }
        else{
            return numFaces
        }

    }
}

class Dice(private val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}