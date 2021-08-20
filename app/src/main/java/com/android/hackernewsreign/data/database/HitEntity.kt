package com.android.hackernewsreign.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "hits")
data class HitEntity (
    @PrimaryKey
    var story_id: Int,
    var created_at: String,
    var story_title: String,
    var author: String,
    var story_url: String
)