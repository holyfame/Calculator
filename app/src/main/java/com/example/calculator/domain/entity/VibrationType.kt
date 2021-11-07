package com.example.calculator.domain.entity

import android.os.VibrationEffect

enum class VibrationType(val value: Vibration) {
    NONE (Vibration(1, VibrationEffect.DEFAULT_AMPLITUDE)),
    LIGHT (Vibration(50, VibrationEffect.EFFECT_TICK)),
    NORMAL(Vibration(100, VibrationEffect.DEFAULT_AMPLITUDE)),
    HEAVY (Vibration(200, VibrationEffect.EFFECT_HEAVY_CLICK))
}

class Vibration(val duration: Long, val type: Int)