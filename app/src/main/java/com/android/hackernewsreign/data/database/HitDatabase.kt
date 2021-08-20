package com.android.hackernewsreign.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HitEntity::class, DeleteHitEntity::class], version = 1, exportSchema = false)
abstract class HitDatabase : RoomDatabase() {

    abstract val hitDao: HitDao

}