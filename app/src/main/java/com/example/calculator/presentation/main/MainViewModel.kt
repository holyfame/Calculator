package com.example.calculator.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var expression: String = ""

    private val _expressionState = MutableLiveData<String>()
    val expressionState : LiveData<String> = _expressionState

    private val _resultState = MutableLiveData<String>()
    val resultState : LiveData<String> = _resultState

    fun onNumberClick(number: Int) {
        expression += number.toString()
        _expressionState.value = expression
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MainViewModel", "onCleared")
    }

}

