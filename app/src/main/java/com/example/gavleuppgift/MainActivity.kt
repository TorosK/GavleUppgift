package com.example.gavleuppgift

// MainActivity.kt
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gavleuppgift.logic.CalculatorLogic
import com.example.gavleuppgift.model.OperationType

class MainActivity : AppCompatActivity() {

    private val calculatorLogic = CalculatorLogic()
    private lateinit var display: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)
    }

    fun onNumberClick(view: View) {
        val button = view as Button
        val currentDisplay = display.text.toString()
        val newDisplay = currentDisplay + button.text
        display.text = newDisplay
        calculatorLogic.setOperand(newDisplay)
    }

    fun onOperationClick(view: View) {
        val button = view as Button
        val operation = when (button.text) {
            "+" -> OperationType.ADD
            "-" -> OperationType.SUBTRACT
            "*" -> OperationType.MULTIPLY
            "/" -> OperationType.DIVIDE
            else -> OperationType.NONE
        }
        calculatorLogic.setOperation(operation)
        display.text = ""
    }

    fun onClearClick(view: View) {
        calculatorLogic.clear()
        display.text = ""
    }

    fun onEqualClick(view: View) {
        val result = calculatorLogic.calculate().toString()
        display.text = result
        calculatorLogic.clear()
    }
}
