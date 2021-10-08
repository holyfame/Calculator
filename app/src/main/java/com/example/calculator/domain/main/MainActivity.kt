package com.example.calculator.domain.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.calculator.presentation.common.BaseActivity
import com.example.calculator.R
import com.example.calculator.presentation.settings.Result
import com.example.calculator.databinding.MainActivityBinding
import com.example.calculator.domain.calculateExpression

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val viewBinding by viewBinding(MainActivityBinding::bind)

    private val getResult = registerForActivityResult(Result()) {
        result -> Toast.makeText(this, "result: $result", Toast.LENGTH_SHORT).show()
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
            textView.setOnClickListener { viewModel.onNumberClick(index, 0) }
        }

        mapOf(
            Operator.PLUS to viewBinding.mainPlus,
            Operator.MINUS to viewBinding.mainMinus,
            Operator.MULTIPLY to viewBinding.mainMultiply,
            Operator.DIVIDE to viewBinding.mainDivide
        ).forEach { (operator, textView) ->
            textView.setOnClickListener { viewModel.onOperatorClick(operator, 0) }
        }

        viewModel.expressionState.observe(this) { state ->
            viewBinding.mainInput.setText(state.expression)
        }


        viewBinding.mainEquals.setOnClickListener {
//            viewBinding.mainResult.text = calculateExpression(viewModel.resultState.value?:"")
            viewBinding.mainResult.text = calculateExpression(viewModel.expressionState.value!!.expression)
        }

        //        viewModel.resultState.observe(this) { state ->
//            viewBinding.mainResult.text = calculateExpression(state)
//        }

//        viewBinding.mainBack.setOnClickListener {
//            viewModel.
//        }


    }

    private fun openSettings() {
        getResult.launch(10)
    }

}