// MainActivity.kt
// Package declaration
package com.example.gavleuppgift

// Import statements
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gavleuppgift.logic.CalculatorLogic
import com.example.gavleuppgift.model.OperationType

// MainActivity class that inherits from AppCompatActivity
class MainActivity : AppCompatActivity() {

    // Declares an instance of CalculatorLogic class for calculation logic
    private val calculatorLogic = CalculatorLogic()

    // Declare a lateinit TextView variable for the display
    private lateinit var display: TextView

    // The onCreate method: Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the display TextView
        display = findViewById(R.id.display)
    }

    // Method triggered when number buttons are clicked
    fun onNumberClick(view: View) {
        // Cast the clicked view to Button
        val button = view as Button

        // Get the current display text
        val currentDisplay = display.text.toString()

        // Append the button's text to the current display
        val newDisplay = currentDisplay + button.text

        // Update the TextView display
        display.text = newDisplay

        // Update the operand in the calculator logic
        calculatorLogic.setOperand(newDisplay)
    }

    // Method triggered when operation buttons are clicked
    fun onOperationClick(view: View) {
        // Cast the clicked view to Button
        val button = view as Button

        // Determine which operation is selected based on the button text
        val operation = when (button.text) {
            "+" -> OperationType.ADD
            "-" -> OperationType.SUBTRACT
            "*" -> OperationType.MULTIPLY
            "/" -> OperationType.DIVIDE
            else -> OperationType.NONE
        }

        // Update the operation in the calculator logic
        calculatorLogic.setOperation(operation)

        // Clear the display for the next input
        display.text = ""
    }

    // Method to clear the calculator's state
    fun onClearClick(view: View) {
        // Clear the calculator logic
        calculatorLogic.clear()

        // Clear the TextView display
        display.text = ""
    }

    // Method to calculate the result when "=" button is clicked
    fun onEqualClick(view: View) {
        // Calculate the result using the calculator logic
        val result = calculatorLogic.calculate().toString()

        // Update the TextView display with the result
        display.text = result

        // Set the result as the new firstOperand for subsequent operations
        calculatorLogic.setOperand(result)

        // Reset the current operation to NONE
        calculatorLogic.setOperation(OperationType.NONE)
    }
}