package com.example.td1

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    val tag = "Hello"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = "Bonjour Val"

        Log.i(tag, text)

        val v = 54
        val n = v / 9 - 2
        Log.i(tag, "n = $n")
        val f: Int = factorielle(n)
        Log.i(tag, "$n! = $f")


    }

    private fun factorielle(n: Int): Int {
        var r: Int = 0
        if (n > 1) {
            var fnm1 = factorielle(n - 1)
            r = n * fnm1
        } else {
            r = 1
        }
        return r
    }
}