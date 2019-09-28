package com.example.runnable

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    private lateinit var mRandom: Random
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRandom = Random()
        mHandler = Handler()

        btn_run_handler.setOnClickListener {
            mRunnable = Runnable {
                root_layout.setBackgroundColor(randomHSVColor())
                textView.text = "Handler Example\n Random Number : ${mRandom.nextInt(100)}"

                mHandler.postDelayed(mRunnable, 1000)
            }

            mHandler.postDelayed(mRunnable, 1000)
        }

        btn_remove_callBack.setOnClickListener {
            mHandler.removeCallbacks(mRunnable)
            textView.text = "Handler call backs removed."
        }

        btn_handler_short_code.setOnClickListener {

            mHandler.postDelayed({
                textView.text = "Handler Short Code\n" +
                        "Random Number : ${mRandom.nextInt(100)}"
                textView.textSize = 25.0F
                textView.setTextColor(randomHSVColor())
                root_layout.setBackgroundColor(Color.WHITE)
            }, 3000)
        }

    }

    fun randomHSVColor(): Int {
        val hue = mRandom.nextInt(361)
        val saturation = 1.0f
        val value = 1.0f
        val alpha = 255
        return Color.HSVToColor(alpha, floatArrayOf(hue.toFloat(), saturation, value))
    }

}