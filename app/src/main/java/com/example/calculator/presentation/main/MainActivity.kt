package com.example.calculator.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.calculator.presentation.common.BaseActivity
import com.example.calculator.R
import com.example.calculator.databinding.MainActivityBinding
import com.example.calculator.di.HistoryRepositoryProvider
import com.example.calculator.di.SettingsDaoProvider
import com.example.calculator.domain.entity.ResultPanelType.LEFT
import com.example.calculator.domain.entity.ResultPanelType.RIGHT
import com.example.calculator.domain.entity.ResultPanelType.HIDE
import com.example.calculator.presentation.history.HistoryResult
import com.example.calculator.presentation.settings.SettingsActivity

class MainActivity : BaseActivity() {

    private val viewBinding by viewBinding(MainActivityBinding::bind)
    private val viewModel: MainViewModel by viewModels() {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(
                    SettingsDaoProvider.get(this@MainActivity),
                    HistoryRepositoryProvider.get(this@MainActivity)
                ) as T
            }
        }
    }

    private val resultLauncher = registerForActivityResult(HistoryResult()) { item ->
        viewModel.onHistoryResult(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewBinding.mainInput.apply {
            showSoftInputOnFocus = false
        }

        viewBinding.mainActivitySettings.setOnClickListener {
            openSettings()
        }

        viewBinding.mainHistory?.setOnClickListener {
            openHistory()
        }

        viewModel.resultPanelState.observe(this) {
            with(viewBinding.mainResult) {
                gravity = when (it) {
                    LEFT -> Gravity.START.or(Gravity.CENTER_VERTICAL)
                    RIGHT -> Gravity.END or (Gravity.CENTER_VERTICAL)
                    HIDE -> gravity
                }
                isVisible = it != HIDE
            }

        }

        listOf(
            viewBinding.main0,
            viewBinding.main1,
            viewBinding.main2,
            viewBinding.main3,
            viewBinding.main4,
            viewBinding.main5,
            viewBinding.main6,
            viewBinding.main7,
            viewBinding.main8,
            viewBinding.main9,
        ).forEachIndexed { index, textView ->
            textView.setOnClickListener {
                viewModel.onNumberClick(index, viewBinding.mainInput.selectionStart)
            }
        }

        mapOf(
            Operator.PLUS to viewBinding.mainPlus,
            Operator.MINUS to viewBinding.mainMinus,
            Operator.MULTIPLY to viewBinding.mainMultiply,
            Operator.DIVIDE to viewBinding.mainDivide,
            Operator.POINT to viewBinding.mainPoint,
            Operator.POWER to viewBinding.mainPower,
            Operator.LEFT_BRACE to viewBinding.mainLeftBrace,
            Operator.RIGHT_BRACE to viewBinding.mainRightBrace
        ).forEach { (operator, textView) ->
            textView?.setOnClickListener {
                viewModel.onOperatorClick(operator, viewBinding.mainInput.selectionStart)
            }
        }

        viewBinding.mainSqrt?.setOnClickListener {
            viewModel.onSqrtClick(viewBinding.mainInput.selectionStart)
        }

        viewModel.expressionState.observe(this) { state ->
            viewBinding.mainInput.setText(state.expression)
            viewBinding.mainInput.setSelection(state.selection)
        }

        viewModel.resultState.observe(this) { state ->
            viewBinding.mainResult.text = state.toString()
        }

        viewBinding.mainEquals.setOnClickListener {
            viewModel.onEqualsClick()
        }

        viewBinding.mainBack.setOnClickListener {
            viewModel.onBackClick(viewBinding.mainInput.selectionStart)
        }

        viewBinding.mainClear.setOnClickListener {
            viewModel.onClearClick()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    private fun openSettings() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    private fun openHistory() {
        resultLauncher.launch()
    }

}