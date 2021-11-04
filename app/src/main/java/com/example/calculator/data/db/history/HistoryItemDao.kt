package com.example.calculator.data.db.history

import androidx.room.*

@Dao
interface HistoryItemDao {

    @Insert
    suspend fun insert(historyItemEntity: HistoryItemEntity)

    @Delete
    suspend fun delete(historyItemEntity: HistoryItemEntity)

    @Delete
    suspend fun delete(historyItemEntities: List<HistoryItemEntity>)

    @Update
    suspend fun update(historyItemEntity: HistoryItemEntity)

    @Query("select * from history_item_entity")
    suspend fun getAll(): List<HistoryItemEntity>

    @Query("delete from history_item_entity")
    suspend fun clear()
}