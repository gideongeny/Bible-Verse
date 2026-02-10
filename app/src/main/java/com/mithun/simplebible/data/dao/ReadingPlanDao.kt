package com.mithun.simplebible.data.dao

import androidx.room.*
import com.mithun.simplebible.data.database.model.ReadingPlan
import com.mithun.simplebible.data.database.model.ReadingProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadingPlanDao {

    @Query("SELECT * FROM reading_plans")
    fun getAllPlans(): Flow<List<ReadingPlan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlan(plan: ReadingPlan): Long

    @Query("SELECT * FROM reading_progress WHERE planId = :planId")
    fun getProgressForPlan(planId: Long): Flow<List<ReadingProgress>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun markChapterCompleted(progress: ReadingProgress)

    @Query("DELETE FROM reading_progress WHERE planId = :planId AND chapterId = :chapterId")
    suspend fun unmarkChapterCompleted(planId: Long, chapterId: String)
}
