package com.mithun.simplebible.data.database.model

import androidx.room.*

@Entity(tableName = "reading_plans")
data class ReadingPlan(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val totalDays: Int,
    val category: String // e.g. "Gospels", "Whole Bible"
)

@Entity(
    tableName = "reading_progress",
    foreignKeys = [
        ForeignKey(
            entity = ReadingPlan::class,
            parentColumns = ["id"],
            childColumns = ["planId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("planId")]
)
data class ReadingProgress(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val planId: Long,
    val chapterId: String,
    val completedAt: Long = System.currentTimeMillis()
)
