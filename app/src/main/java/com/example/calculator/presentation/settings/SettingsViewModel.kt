package com.example.calculator.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.domain.SettingsDao
import com.example.calculator.domain.entity.ResultPanelType
import com.example.calculator.domain.entity.VibrationType
import com.example.calculator.presentation.common.SingleLiveEvent
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsDao: SettingsDao
) : ViewModel() {

    private val _resultPanelState = MutableLiveData(ResultPanelType.LEFT)
    val resultPanelState: LiveData<ResultPanelType> = _resultPanelState

    private val _openResultPanelAction = SingleLiveEvent<ResultPanelType>()
    val openResultPanelAction: LiveData<ResultPanelType> = _openResultPanelAction

    private val _openVibrationType = SingleLiveEvent<VibrationType>()
    val openVibrationType: LiveData<VibrationType> = _openVibrationType

    private val _answerPrecision = SingleLiveEvent<Int>()
    val answerPrecision: LiveData<Int> = _answerPrecision

    private val _vibrationType = SingleLiveEvent<VibrationType>()
    val vibrationType: LiveData<VibrationType> = _vibrationType

    init {
        viewModelScope.launch {
            _resultPanelState.value = settingsDao.getResultPanelType()
            _answerPrecision.value = settingsDao.getAnswerPrecision()
            _vibrationType.value = settingsDao.getVibrationType()
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

    fun onVibrationTypeChanged(vibration: VibrationType) {
        _vibrationType.value = vibration
        viewModelScope.launch {
            settingsDao.setVibrationType(vibration)
        }
    }

    fun onResultPanelTypeClicked() {
        _openResultPanelAction.value = resultPanelState.value
    }

    fun onVibrationTypeClicked() {
        _openVibrationType.value = vibrationType.value
    }

}

