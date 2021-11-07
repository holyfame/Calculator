package com.example.calculator.presentation.settings

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.calculator.presentation.common.BaseActivity
import com.example.calculator.R
import com.example.calculator.databinding.SettingsActivityBinding
import com.example.calculator.di.SettingsDaoProvider
import com.example.calculator.domain.entity.ResultPanelType
import com.example.calculator.domain.entity.VibrationType
import java.lang.Integer.min

class SettingsActivity : BaseActivity() {

    private val viewBinding by viewBinding(SettingsActivityBinding::bind)
    private val viewModel by viewModels<SettingsViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SettingsViewModel(SettingsDaoProvider.get(this@SettingsActivity)) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        viewBinding.settingsBack.setOnClickListener {
            finish()
        }

        viewBinding.resultPanelContainer.setOnClickListener {
            viewModel.onResultPanelTypeClicked()
        }

        viewBinding.precisionContainer.setOnClickListener {
            showAnswerPrecisionDialog()
        }

        viewBinding.vibrationContainer.setOnClickListener {
            viewModel.onVibrationTypeClicked()
        }

        viewModel.resultPanelState.observe(this) { state ->
            viewBinding.resultPanelDescription.text =
                resources.getStringArray(R.array.result_panel_types)[state.ordinal]
        }

        viewModel.answerPrecision.observe(this) { _ ->
            viewBinding.precisionValue.text =
                resources.getString(R.string.digits_after_point, viewModel.answerPrecision.value)
        }

        viewModel.vibrationType.observe(this) { state ->
            viewBinding.vibrationDescription.text =
                resources.getStringArray(R.array.vibration_types)[state.ordinal]
        }

        viewModel.openResultPanelAction.observe(this) { type ->
            showResultPanelDialog(type)
        }

        viewModel.openVibrationType.observe(this) { type ->
            showVibrationDialog(type)
        }
    }

    private fun showResultPanelDialog(type: ResultPanelType) {
        var result: Int? = null
        val builder = AlertDialog.Builder(this)
        with (builder) {
            setTitle(getString(R.string.settings_result_panel_title))
            setPositiveButton("Ok") { _, _ ->
                result?.let {
                    viewModel.onResultPanelTypeChanged(ResultPanelType.values()[it])
                }
            }
            setNegativeButton("Cancel") { _, _ ->

            }
            setSingleChoiceItems(R.array.result_panel_types, type.ordinal) { _, id ->
                result = id
            }
            show()
        }
    }

    private fun showAnswerPrecisionDialog() {
        val builder = AlertDialog.Builder(this)
        val input = EditText(this)
        with (input) {
            hint = "Enter number <= $MAX_DIGITS_AFTER_POINT"
            inputType = InputType.TYPE_CLASS_NUMBER
        }
        with (builder) {
            setTitle(getString(R.string.settings_answer_precision_title))
            setView(input)
            setPositiveButton("Ok") { _, _ ->
                input.text.toString().toIntOrNull()?.let {
                    viewModel.onAnswerPrecisionChanged(min(it, MAX_DIGITS_AFTER_POINT))
                }
            }
            setNegativeButton("Cancel") { _, _ ->

            }
            show()
        }
    }

    private fun showVibrationDialog(type: VibrationType) {
        var result: Int? = null
        val builder = AlertDialog.Builder(this)
        with (builder) {
            setTitle(getString(R.string.settings_vibration_title))
            setPositiveButton("Ok") { _, _ ->
                result?.let {
                    viewModel.onVibrationTypeChanged(VibrationType.values()[it])
                }
            }
            setNegativeButton("Cancel") { _, _ ->

            }
            setSingleChoiceItems(R.array.vibration_types, type.ordinal) { _, id ->
                result = id
            }
            show()
        }
    }

    companion object {
        private const val MAX_DIGITS_AFTER_POINT = 10
    }

}