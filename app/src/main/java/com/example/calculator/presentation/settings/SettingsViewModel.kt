package com.example.calculator.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.domain.SettingsDao
import com.example.calculator.domain.entity.ResultPanelType
import com.example.calculator.presentation.common.SingleLiveEvent
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsDao: SettingsDao
) : ViewModel() {

    private val _resultPanelState = MutableLiveData(ResultPanelType.LEFT)
    val resultPanelState: LiveData<ResultPanelType> = _resultPanelState

    private val _openResultPanelAction = SingleLiveEvent<ResultPanelType>()
    val openResultPanelAction: LiveData<ResultPanelType> = _openResultPanelAction

    private val _answerPrecision = SingleLiveEvent<Int>()
    val answerPrecision: LiveData<Int> = _answerPrecision

    init {
        viewModelScope.launch {
            _resultPanelState.value = settingsDao.getResultPanelType()
            _answerPrecision.value = settingsDao.getAnswerPrecision()
        }
    }

    fun onResultPanelTypeChanged(resultPanelType: ResultPanelType) {
        _resultPanelState.value = resultPanelType
        viewModelScope.launch {
            settingsDao.setResultPanelType(resultPanelType)
        }
    }

    fun onAnswerPrecisionChanged(precision: Int) {
        _answerPrecision.value = precision
        viewModelScope.launch {
            settingsDao.setAnswerPrecision(precision)
        }
    }

    fun onResultPanelTypeClicked() {
        _openResultPanelAction.value = resultPanelState.value
    }
}

