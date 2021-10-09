package com.example.calculator.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.StringBuilder
import android.text.SpannableStringBuilder
import com.example.calculator.domain.calculateExpression
import kotlin.math.exp


class MainViewModel : ViewModel() {

    private var expression: String = ""

    private val _expressionState = MutableLiveData(ExpressionState(expression, 0))
    val expressionState : LiveData<ExpressionState> = _expressionState

    private val _resultState = MutableLiveData<String>()
    val resultState : LiveData<String> = _resultState

    fun onNumberClick(number: Int, selection: Int) {
        expression = putInSelection(expression, number.toString(), selection)
        _expressionState.value = ExpressionState(expression, selection + 1)
    }

    fun onOperatorClick(operator: Operator, selection: Int) {
        expression = putInSelection(expression, operator.symbol, selection)
        _expressionState.value = ExpressionState(expression, selection + 1)
    }

    fun onBackClick(selection: Int) {
        if (selection <= 0) {
            return
        }
        expression = StringBuilder(expression).deleteAt(selection - 1).toString()
        _expressionState.value = ExpressionState(expression, selection - 1)
    }

    fun onClearClick() {
        expression = ""
        _expressionState.value = ExpressionState(expression, 0)
        _resultState.value = ""
    }

//    private fun String.putInSelection(expression: put: String, selection: Int) {
//        expression.
//    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MainViewModel", "onCleared")
    }

    private fun putInSelection(expression: String, put: String, selection: Int): String {
        return expression.substring(0, selection) + put + expression.substring(
            selection,
            expression.length
        )
    }

    fun onEqualsClick() {
        try {
            _resultState.value = calculateExpression(expression)
        } catch (e: java.lang.IllegalArgumentException) {
            // do nothing
        }
    }

}

enum class Operator(val symbol: String) {
    MINUS("-"), PLUS("+"), MULTIPLY("*"), DIVIDE("/")
}

class ExpressionState(val expression: String, val selection: Int)

