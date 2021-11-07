package com.example.calculator.domain

import com.example.calculator.domain.entity.ResultPanelType
import com.example.calculator.domain.entity.VibrationType

interface SettingsDao {

    /**
     * receives presentation type or result panel
     */
    suspend fun getResultPanelType(): ResultPanelType

    /**
     * sets presentation type of result panel
     */
    suspend fun setResultPanelType(resultPanelType: ResultPanelType)

    suspend fun getAnswerPrecision(): Int

    suspend fun setAnswerPrecision(answerPrecision: Int)

    suspend fun getVibrationType(): VibrationType

    suspend fun setVibrationType(vibrationType: VibrationType)

}

