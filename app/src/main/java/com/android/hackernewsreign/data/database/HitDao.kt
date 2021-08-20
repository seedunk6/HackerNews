package com.android.hackernewsreign.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HitDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHits(hits: List<HitEntity>)

    @Query("SELECT * FROM hits ORDER BY created_at DESC")
    suspend fun getAllHits(): List<HitEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDeleteIdHits(delete: DeleteHitEntity)

    @Query("SELECT * FROM delete_hits")
    suspend fun getDeleteHits(): List<DeleteHitEntity>

}