package com.example.calculator.presentation.main

import com.example.calculator.domain.SettingsDao
import com.example.calculator.domain.entity.ResultPanelType

class SettingsDaoFake : SettingsDao {

    private var resultPanelType: ResultPanelType = ResultPanelType.LEFT
    private var answerPrecision: Int = 3

    override suspend fun setResultPanelType(resultPanelType: ResultPanelType) {
        this.resultPanelType = resultPanelType
    }

    override suspend fun getResultPanelType(): ResultPanelType {
        return resultPanelType
    }

    override suspend fun setAnswerPrecision(answerPrecision: Int) {
        this.answerPrecision = answerPrecision
    }

    override suspend fun getAnswerPrecision(): Int {
        return answerPrecision
    }

}