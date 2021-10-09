package com.example.calculator.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testPlus() {
        val viewModel = MainViewModel()

        viewModel.onNumberClick(2, 0)
        viewModel.onOperatorClick(Operator.PLUS, 1)
        viewModel.onNumberClick(2, 2)
        viewModel.onEqualsClick()

        Assert.assertEquals("2+2", viewModel.expressionState.value?.expression)
        Assert.assertEquals("4", viewModel.resultState.value)
    }

    @Test
    fun testAsDivide() {
        val viewModel = MainViewModel()

        viewModel.onNumberClick(1, 0)
        viewModel.onNumberClick(0, 1)
        viewModel.onOperatorClick(Operator.DIVIDE, 2)
        viewModel.onNumberClick(2, 3)
        viewModel.onEqualsClick()

        Assert.assertEquals("10/2", viewModel.expressionState.value?.expression)
        Assert.assertEquals("5", viewModel.resultState.value)
    }


}