package com.android.hackernewsreign.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "delete_hits")
data class DeleteHitEntity (
    @PrimaryKey
    var story_id: Int
)