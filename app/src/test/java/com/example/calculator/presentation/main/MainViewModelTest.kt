package com.example.calculator.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.calculator.domain.HistoryRepository
import com.example.calculator.domain.SettingsDao
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val settigsDao: SettingsDao = SettingsDaoFake()
    private val historyRepository: HistoryRepository = HistoryRepositoryFake()

    @Test
    fun testPlus() {
        val viewModel = MainViewModel(settigsDao, historyRepository)

        viewModel.onNumberClick(2, 0)
        viewModel.onOperatorClick(Operator.PLUS, 1)
        viewModel.onNumberClick(2, 2)
        viewModel.onEqualsClick()

        Assert.assertEquals("2+2", viewModel.expressionState.value?.expression)
        Assert.assertEquals("4", viewModel.resultState.value)
    }

    @Test
    fun testAsDivide() {
        val viewModel = MainViewModel(settigsDao, historyRepository)

        viewModel.onNumberClick(1, 0)
        viewModel.onNumberClick(0, 1)
        viewModel.onOperatorClick(Operator.DIVIDE, 2)
        viewModel.onNumberClick(2, 5)
        viewModel.onEqualsClick()

        Assert.assertEquals("10/5", viewModel.expressionState.value?.expression)
        Assert.assertEquals("2", viewModel.resultState.value)
    }

}

