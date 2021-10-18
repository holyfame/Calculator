package com.example.calculator.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.data.SettingsDaoImpl
import com.example.calculator.di.SettingsDaoProvider
import com.example.calculator.domain.SettingsDao
import com.example.calculator.domain.entity.ResultPanelType
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val settigsDao: SettingsDao = SettingsDaoFake()

    @Test
    fun testPlus() {
        val viewModel = MainViewModel(settigsDao)

        viewModel.onNumberClick(2, 0)
        viewModel.onOperatorClick(Operator.PLUS, 1)
        viewModel.onNumberClick(2, 2)
        viewModel.onEqualsClick()

        Assert.assertEquals("2+2", viewModel.expressionState.value?.expression)
        Assert.assertEquals("4", viewModel.resultState.value)
    }

    @Test
    fun testAsDivide() {
        val viewModel = MainViewModel(settigsDao)

        viewModel.onNumberClick(1, 0)
        viewModel.onNumberClick(0, 1)
        viewModel.onOperatorClick(Operator.DIVIDE, 2)
        viewModel.onNumberClick(2, 5)
        viewModel.onEqualsClick()

        Assert.assertEquals("10/5", viewModel.expressionState.value?.expression)
        Assert.assertEquals("2", viewModel.resultState.value)
    }

}

class SettingsDaoFake : SettingsDao {

    private var resultPanelType: ResultPanelType = ResultPanelType.LEFT

    override suspend fun setResultPanelType(resultPanelType: ResultPanelType) {
        this.resultPanelType = resultPanelType
    }

    override suspend fun getResultPanelType(): ResultPanelType {
        return resultPanelType
    }

}