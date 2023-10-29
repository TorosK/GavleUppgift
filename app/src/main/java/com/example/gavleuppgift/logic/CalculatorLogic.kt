package com.example.gavleuppgift.logic

// CalculatorLogic.kt
import com.example.gavleuppgift.model.OperationType
import java.math.BigDecimal

class CalculatorLogic {
    private var firstOperand = BigDecimal.ZERO
    private var secondOperand = BigDecimal.ZERO
    private var currentOperation = OperationType.NONE

    fun setOperand(value: String) {
        if (currentOperation == OperationType.NONE) {
            firstOperand = BigDecimal(value)
        } else {
            secondOperand = BigDecimal(value)
        }
    }

    fun setOperation(operation: OperationType) {
        currentOperation = operation
    }

    fun calculate(): BigDecimal {
        return when (currentOperation) {
            OperationType.ADD -> firstOperand + secondOperand
            OperationType.SUBTRACT -> firstOperand - secondOperand
            OperationType.MULTIPLY -> firstOperand * secondOperand
            OperationType.DIVIDE -> firstOperand.divide(secondOperand)
            else -> BigDecimal.ZERO
        }
    }

    fun clear() {
        firstOperand = BigDecimal.ZERO
        secondOperand = BigDecimal.ZERO
        currentOperation = OperationType.NONE
    }
}