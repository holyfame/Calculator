package com.example.calculator.presentation.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.domain.HistoryRepository
import com.example.calculator.domain.entity.HistoryItem
import com.example.calculator.presentation.common.SingleLiveEvent
import kotlinx.coroutines.launch

class HistoryViewModel (
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private val _historyItemsState = MutableLiveData<List<HistoryItem>>()
    val historyItemsState = _historyItemsState

    private val _closeWithResult = SingleLiveEvent<HistoryItem>()
    val closeWithResult = _closeWithResult

    init {
        viewModelScope.launch {
            _historyItemsState.value = historyRepository.getAll()
        }
    }

    fun onItemClicked(historyItem: HistoryItem) {
        _closeWithResult.value = historyItem
    }

    fun clearHistory() {
        viewModelScope.launch {
            historyRepository.clear()
            _historyItemsState.value = historyRepository.getAll()
        }
    }

}

