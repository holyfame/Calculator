package com.example.calculator.presentation.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.entity.HistoryItem
import com.example.calculator.presentation.common.SingleLiveEvent

class HistoryViewModel : ViewModel() {

    private val historyItems: List<HistoryItem> = listOf (
        HistoryItem("2 + 35 + 54252 + 123", "12313515"),
        HistoryItem("2 + 35 + 54252 + 123", "12313515"),
        HistoryItem("2 + 35 + 54252 + 123", "12313515"),
        HistoryItem("2 + 35 + 54252 + 123", "12313515"),
        HistoryItem("2 + 35 + 54252 + 123", "12313515")
    )

    private val _historyItemsState = MutableLiveData<List<HistoryItem>>()
    val historyItemsState = _historyItemsState

    private val _showToastAction = SingleLiveEvent<HistoryItem>()
    val showToastAction = _showToastAction

    init {
        _historyItemsState.value = historyItems
    }

    fun onItemClicked(historyItem: HistoryItem) {
        _showToastAction.value = historyItem
    }

}

