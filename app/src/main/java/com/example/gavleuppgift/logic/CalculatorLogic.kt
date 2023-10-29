// CalculatorLogic.kt
// Package declaration
package com.example.gavleuppgift.logic

// Importing necessary classes
import com.example.gavleuppgift.model.OperationType
import java.math.BigDecimal
import java.math.MathContext

// CalculatorLogic class definition
class CalculatorLogic {
    // Private variables to hold the operands and the type of operation
    private var firstOperand = BigDecimal.ZERO
    private var secondOperand = BigDecimal.ZERO
    private var currentOperation = OperationType.NONE

    // Function to set operand values
    fun setOperand(value: String) {
        // If no operation has been set yet, assign value to firstOperand
        if (currentOperation == OperationType.NONE) {
            firstOperand = BigDecimal(value)
        } else {
            // Otherwise, assign it to the secondOperand
            secondOperand = BigDecimal(value)
        }
    }

    // Function to set the type of arithmetic operation
    fun setOperation(operation: OperationType) {
        currentOperation = operation
    }

    // Function to calculate the result based on operands and operation type
    fun calculate(): BigDecimal {
        // Using 'when' expression to perform the appropriate arithmetic operation
        return when (currentOperation) {
            // If addition, then add the two operands
            OperationType.ADD -> firstOperand + secondOperand

            // If subtraction, then subtract the second operand from the first
            OperationType.SUBTRACT -> firstOperand - secondOperand

            // If multiplication, then multiply the two operands
            OperationType.MULTIPLY -> firstOperand * secondOperand

            // If division, then divide the first operand by the second
            OperationType.DIVIDE -> {
                // Check if the second operand is zero to handle divide-by-zero case
                if (secondOperand == BigDecimal.ZERO) {
                    // Handle divide by zero
                    BigDecimal.ZERO
                } else {
                    // Divide using the specified math context for precision
                    firstOperand.divide(secondOperand, MathContext.DECIMAL128)
                }
            }

            // If no operation is set, return zero
            else -> BigDecimal.ZERO
        }
    }

    // Function to clear the stored operands and operation type
    fun clear() {
        // Reset operands and operation type to default values
        firstOperand = BigDecimal.ZERO
        secondOperand = BigDecimal.ZERO
        currentOperation = OperationType.NONE
    }
}