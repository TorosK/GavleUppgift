// MainActivity.kt
// Package declaration - specifies the namespace for this class.
package com.example.gavleuppgift

// Import necessary classes and components from the Android framework.
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gavleuppgift.logic.CalculatorLogic
import com.example.gavleuppgift.model.OperationType

// MainActivity class that represents the main screen of the app. It inherits from AppCompatActivity,
// which provides compatibility features and basic activity lifecycle handling.
class MainActivity : AppCompatActivity() {

    // Create an instance of the CalculatorLogic class to handle the arithmetic operations.
    private val calculatorLogic = CalculatorLogic()

    // Declare a reference for the display TextView where the user's input and calculation results will be shown.
    private lateinit var display: TextView

    // This method is called when the activity is first created. It sets up the initial state of the activity.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Associate this activity with its layout file (activity_main.xml).
        setContentView(R.layout.activity_main)

        // Link the display variable to the actual TextView in the layout.
        display = findViewById(R.id.display)
    }

    // This method is called when any of the number buttons (0-9) is clicked.
    fun onNumberClick(view: View) {
        // Convert the clicked view to a Button to access its text property.
        val button = view as Button

        // Get the number or symbol currently displayed on the calculator's screen.
        val currentDisplay = display.text.toString()

        // Append the text of the clicked button to the current display.
        val newDisplay = currentDisplay + button.text

        // Show the updated number or symbol sequence on the screen.
        display.text = newDisplay

        // Pass the current displayed value to the calculator logic for further processing.
        calculatorLogic.setOperand(newDisplay)
    }

    // This method is called when an operation button (+, -, *, /) is clicked.
    fun onOperationClick(view: View) {
        // Convert the clicked view to a Button.
        val button = view as Button

        // Determine the type of arithmetic operation based on the button's text.
        val operation = when (button.text) {
            "+" -> OperationType.ADD
            "-" -> OperationType.SUBTRACT
            "*" -> OperationType.MULTIPLY
            "/" -> OperationType.DIVIDE
            // In case an unknown button is clicked, set the operation to NONE.
            else -> OperationType.NONE
        }

        // Pass the selected operation type to the calculator logic.
        calculatorLogic.setOperation(operation)

        // Clear the display to allow the user to input the second number.
        display.text = ""
    }

    // This method is called when the "C" (clear) button is clicked. It resets the calculator's state.
    fun onClearClick(view: View) {
        // Reset the calculator logic to its initial state.
        calculatorLogic.clear()

        // Clear the display.
        display.text = ""
    }

    // This method is triggered when the "=" (equal) button is clicked to compute the result of the operation.
    fun onEqualClick(view: View) {
        // Calculate the result of the operation.
        val result = calculatorLogic.calculate().toString()

        // Display the result on the calculator's screen.
        display.text = result

        // Store the result in the calculator logic so that it can be used in the next calculation.
        calculatorLogic.setOperand(result)

        // Reset the operation type to NONE.
        calculatorLogic.setOperation(OperationType.NONE)
    }
}