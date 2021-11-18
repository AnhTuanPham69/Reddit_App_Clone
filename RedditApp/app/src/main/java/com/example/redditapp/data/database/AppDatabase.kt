package com.example.redditapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.redditapp.data.database.dao.PostDao
import com.example.redditapp.data.database.model.PostDbModel

/**
 * App's database.
 *
 * It contains a table for posts
 */
@Database(entities = [PostDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

  companion object {
    const val DATABASE_NAME = "jet-reddit-database"
  }

  abstract fun postDao(): PostDao
}