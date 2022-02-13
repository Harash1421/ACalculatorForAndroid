package com.mms.acalculator

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    var isNewOp = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Button Click Tick
        var buttonTick = MediaPlayer.create(this, R.raw.button_tick)
        //Button Clear
        buClear.setOnClickListener{
            tvShow.text = "0"
            isNewOp = true
        }
        //Buttons Input
        bu_Bracket_Left.setOnClickListener {
            tvShow.text = addToInputText("(")
        }
        bu_Bracket_Right.setOnClickListener {
            tvShow.text = addToInputText(")")
        }
        bu_Plus0.setOnClickListener {
            tvShow.text = addToInputText("00")
        }
        bu0.setOnClickListener {
            tvShow.text = addToInputText("0")
        }
        bu1.setOnClickListener {
            tvShow.text = addToInputText("1")
        }
        bu2.setOnClickListener {
            tvShow.text = addToInputText("2")
        }
        bu3.setOnClickListener {
            tvShow.text = addToInputText("3")
        }
        bu4.setOnClickListener {
            tvShow.text = addToInputText("4")
        }
        bu5.setOnClickListener {
            tvShow.text = addToInputText("5")
        }
        bu6.setOnClickListener {
            tvShow.text = addToInputText("6")
        }
        bu7.setOnClickListener {
            tvShow.text = addToInputText("7")
        }
        bu8.setOnClickListener {
            tvShow.text = addToInputText("8")
        }
        bu9.setOnClickListener {
            tvShow.text = addToInputText("9")
        }
        bu_Dot.setOnClickListener {
            tvShow.text = addToInputText(".")
        }
        buPlus.setOnClickListener {
            tvShow.text = addToInputText("+")
        }
        buMinus.setOnClickListener {
            tvShow.text = addToInputText("-")
        }
        buMultiplied.setOnClickListener {
            tvShow.text = addToInputText("x")
        }
        buDivided.setOnClickListener {
            tvShow.text = addToInputText("÷")
        }
        buEqual.setOnClickListener {
            isNewOp = false
            showResult()
        }
    }

    private fun addToInputText(button: String): String{
        if(isNewOp){
            tvShow.text = ""
        }
        isNewOp = false
        return "${tvShow.text}$button"
    }

    private fun getShowExpressions(): String{
        var expression = tvShow.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("x"), "*")
        return expression
    }

    @SuppressLint("SetTextI18n")
    private fun showResult() {
        try {
            val expression = getShowExpressions()
            var result = org.mariuszgromada.math.mxparser.Expression(expression).calculate()
            if (result.isNaN()){
                tvShow.text = "0"
            }else{
                tvShow.text = DecimalFormat("0.#####").format(result).toString()
            }
        }catch (e: Exception){
            Log.d("Exception", "$e")
        }

    }
}