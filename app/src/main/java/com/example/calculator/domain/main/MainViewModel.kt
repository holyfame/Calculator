package com.example.calculator.domain.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.calculateExpression

class MainViewModel : ViewModel() {

    private var expression: String = ""

//    private val _expressionState = MutableLiveData<String>()
//    val expressionState : LiveData<String> = _expressionState
//
//    private val _resultState = MutableLiveData<String>()
//    val resultState : LiveData<String> = _resultState

    private val _expressionState = MutableLiveData(ExpressionState(expression, 0))
    val expressionState : LiveData<ExpressionState> = _expressionState
//
//    private val _resultState = MutableLiveData<String>()
//    val resultState : LiveData<String> = _resultState

    fun onNumberClick(number: Int, selection: Int) {
        expression += number.toString()
        _expressionState.value = ExpressionState(expression, selection + 1)
//        _resultState.value = calculateExpression(expression)
    }

    fun onOperatorClick(operator: Operator, selection: Int) {
        expression += operator.symbol
        _expressionState.value = ExpressionState(expression, selection + 1)
    }

    fun onBackClick() {
//        expression
    }

//    private fun String.putInSelection(expression: put: String, selection: Int) {
//        expression.
//    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MainViewModel", "onCleared")
    }

}

enum class Operator(val symbol: String) {
    MINUS("-"), PLUS("+"), MULTIPLY("*"), DIVIDE("/")
}

class ExpressionState(val expression: String, val selection: Int)

