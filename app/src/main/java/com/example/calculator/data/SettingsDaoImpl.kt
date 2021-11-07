package com.example.calculator.data

import android.content.SharedPreferences
import android.os.VibrationEffect
import com.example.calculator.domain.SettingsDao
import com.example.calculator.domain.entity.ResultPanelType
import com.example.calculator.domain.entity.VibrationType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingsDaoImpl (
    private val preferences: SharedPreferences
) : SettingsDao {

    override suspend fun getResultPanelType(): ResultPanelType = withContext(Dispatchers.IO) {
        preferences.getString(RESULT_PANEL_TYPE_KEY, null)
        ?.let { ResultPanelType.valueOf(it) } ?: ResultPanelType.LEFT
    }

    override suspend fun setResultPanelType(resultPanelType: ResultPanelType) = withContext(Dispatchers.IO) {
        preferences.edit().putString(RESULT_PANEL_TYPE_KEY, resultPanelType.name).apply()
    }

    override suspend fun getAnswerPrecision(): Int {
        return preferences.getInt(ANSWER_PRECISION_KEY, DEFAULT_PRECISION)
    }

    override suspend fun setAnswerPrecision(answerPrecision: Int) {
        preferences.edit().putInt(ANSWER_PRECISION_KEY, answerPrecision).apply()
    }

    override suspend fun getVibrationType(): VibrationType {
        val type = preferences.getInt(VIBRATION_TYPE_KEY, VibrationType.NONE.value.type)
        return VibrationType.values().find { it.value?.type == type }?:VibrationType.NONE
    }

    override suspend fun setVibrationType(vibrationType: VibrationType) {
        preferences.edit().putInt(VIBRATION_TYPE_KEY, vibrationType.value.type).apply()
    }

    companion object {
        private const val RESULT_PANEL_TYPE_KEY = "RESULT_PANEL_TYPE_KEY"
        private const val ANSWER_PRECISION_KEY = "ANSWER_PRECISION_KEY"
        private const val VIBRATION_TYPE_KEY = "VIBRATION_TYPE_KEY"

        private const val DEFAULT_PRECISION = 3
    }

}