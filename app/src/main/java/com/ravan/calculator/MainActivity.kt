package com.ravan.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    private var lastNumber : Boolean = false
    private var lastDot : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onclick(view: View){
        tvInput?.append((view as Button).text)
        lastNumber = true
        lastDot = false

    }

    fun onClear(view: View){
        tvInput?.text = ""
    }
    fun onDecimalClick(view: View){
        if(lastNumber && !lastDot){
            tvInput?.append(".")
            lastNumber = false
            lastDot = true
        }

    }
    fun canOperatorClick(view: View) {
        // sjekk sist nummer og operatør
        tvInput?.text?.let{
            if (lastNumber && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumber = false
                lastDot = false
            }
        }
    }

    fun onEqual(view : View){
        if(lastNumber){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try{
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    //starter på index 1
                    tvValue = tvValue.substring(1)


                }
                if (tvValue.contains("-")) {
                    //dele input som array
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = (one.toDouble() - two.toDouble()).toString()
                }
                else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = (one.toDouble() + two.toDouble()).toString()
                }
                else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = (one.toDouble() * two.toDouble()).toString()
                }
                else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = (one.toDouble() / two.toDouble()).toString()
                }
                    //kalkulasjon som ikke vil funke
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun isOperatorAdded(value: String) : Boolean{
        // - false
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

}
