package com.example.calculator.domain

import com.example.calculator.domain.entity.ResultPanelType

interface SettingsDao {

    /**
     * receives presentation type or result panel
     */
    suspend fun getResultPanelType(): ResultPanelType

    /**
     * sets presentation type of result panel
     */
    suspend fun setResultPanelType(resultPanelType: ResultPanelType)

}

