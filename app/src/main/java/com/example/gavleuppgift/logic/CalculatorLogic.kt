// CalculatorLogic.kt
// Package declaration - this defines the namespace for the class.
package com.example.gavleuppgift.logic

// Necessary imports for the class functionality.
import com.example.gavleuppgift.model.OperationType
import java.math.BigDecimal
import java.math.MathContext

// The CalculatorLogic class is responsible for handling the calculation logic for the calculator app.
class CalculatorLogic {
    // Declare variables to store the values of the two operands, the chosen operation, and a flag to identify new operations.
    private var firstOperand = BigDecimal.ZERO
    private var secondOperand = BigDecimal.ZERO
    private var currentOperation = OperationType.NONE
    private var isNewOperation = false

    // This function sets the value of either the first or the second operand, based on the current state of the calculator.
    fun setOperand(value: String) {
        if (isNewOperation) {
            firstOperand = BigDecimal(value)
            isNewOperation = false
        } else if (currentOperation == OperationType.NONE) {
            firstOperand = BigDecimal(value)
        } else {
            secondOperand = BigDecimal(value)
        }
    }

    // Set the desired arithmetic operation (add, subtract, multiply, divide) for the calculator.
    fun setOperation(operation: OperationType) {
        currentOperation = operation
    }

    // Based on the selected operation and the provided operands, compute and return the result.
    fun calculate(): BigDecimal {
        // Switch on the current operation type to perform the required calculation.
        val result = when (currentOperation) {
            // If addition, add the two operands.
            OperationType.ADD -> firstOperand + secondOperand

            // Subtract the second operand from the first.
            OperationType.SUBTRACT -> firstOperand - secondOperand

            // Multiply the two operands.
            OperationType.MULTIPLY -> firstOperand * secondOperand

            // Divide the first operand by the second.
            // Special handling is required to avoid division by zero.
            OperationType.DIVIDE -> {
                // If the denominator (secondOperand) is zero, return zero to avoid a crash.
                if (secondOperand == BigDecimal.ZERO) {
                    BigDecimal.ZERO
                } else {
                    // Use BigDecimal's divide function with a specified math context to handle precision during division.
                    firstOperand.divide(secondOperand, MathContext.DECIMAL128)
                }
            }

            // If no operation is chosen, return zero.
            else -> BigDecimal.ZERO
        }

        // Set the computed result as the new firstOperand for any subsequent calculations.
        firstOperand = result
        isNewOperation = true
        return result
    }

    // Reset the operands and operation type to their initial states.
    fun clear() {
        firstOperand = BigDecimal.ZERO
        secondOperand = BigDecimal.ZERO
        currentOperation = OperationType.NONE
    }
}