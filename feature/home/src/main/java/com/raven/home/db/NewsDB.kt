package com.raven.home.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsModelDB::class] , version = 1)
abstract class NewsDB  (): RoomDatabase() {
        abstract fun ravenNews():NewsDao
}