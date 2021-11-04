package com.example.calculator.presentation.main

import com.example.calculator.domain.HistoryRepository
import com.example.calculator.domain.entity.HistoryItem

class HistoryRepositoryFake : HistoryRepository {
    override suspend fun add(historyItem: HistoryItem) {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<HistoryItem> {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }
}