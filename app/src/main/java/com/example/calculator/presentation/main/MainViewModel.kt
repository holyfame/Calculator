package com.example.calculator.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.domain.HistoryRepository
import com.example.calculator.domain.SettingsDao
import com.example.calculator.domain.calculateExpression
import com.example.calculator.domain.entity.HistoryItem
import com.example.calculator.domain.entity.ResultPanelType
import kotlinx.coroutines.launch
import java.time.LocalDateTime


class MainViewModel (
    private val settingsDao: SettingsDao,
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private var expression: String = ""

    private val _expressionState = MutableLiveData(ExpressionState(expression, 0))
    val expressionState : LiveData<ExpressionState> = _expressionState

    private val _resultState = MutableLiveData<String>()
    val resultState : LiveData<String> = _resultState

    private val _resultPanelState = MutableLiveData<ResultPanelType>(ResultPanelType.LEFT)
    val resultPanelState: LiveData<ResultPanelType> = _resultPanelState

    fun onNumberClick(number: Int, selection: Int) {
        expression = putInSelection(expression, number.toString(), selection)
        _expressionState.value = ExpressionState(expression, selection + 1)
    }

    fun onOperatorClick(operator: Operator, selection: Int) {
        expression = putInSelection(expression, operator.symbol, selection)
        _expressionState.value = ExpressionState(expression, selection + 1)
    }

    fun onSqrtClick(selection: Int) {
        onOperatorClick(Operator.POWER, selection)
        onOperatorClick(Operator.LEFT_BRACE, selection + 1)
        onNumberClick(0, selection + 2)
        onOperatorClick(Operator.POINT, selection + 3)
        onNumberClick(5, selection + 4)
        onOperatorClick(Operator.RIGHT_BRACE, selection + 5)
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

    fun onEqualsClick() {
        viewModelScope.launch {
            try {
                val precision = settingsDao.getAnswerPrecision()
                val result = calculateExpression(expression, precision)
                _resultState.value = result
                viewModelScope.launch {
                    historyRepository.add(HistoryItem(
                        expression,
                        result,
                        LocalDateTime.now()
                    ))
                }
            } catch (e: java.lang.IllegalArgumentException) {
                // do nothing
            }
        }
    }

    fun onStart() {
        viewModelScope.launch {
            _resultPanelState.value = settingsDao.getResultPanelType()
        }
    }

    private fun putInSelection(expression: String, put: String, selection: Int): String {
        return expression.substring(0, selection) + put + expression.substring(
            selection,
            expression.length
        )
    }

    fun onHistoryResult(item: HistoryItem?) {
        if (item != null) {
            expression = item.expression
            _expressionState.value = ExpressionState(expression, expression.length)
            _resultState.value = item.result
        }
    }

}

