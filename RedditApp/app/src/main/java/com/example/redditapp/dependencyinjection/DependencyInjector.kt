package com.example.redditapp.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.example.redditapp.data.database.AppDatabase
import com.example.redditapp.data.database.dbmapper.DbMapper
import com.example.redditapp.data.database.dbmapper.DbMapperImpl
import com.example.redditapp.data.repository.Repository
import com.example.redditapp.data.repository.RepositoryImpl

/**
 * Provides dependencies across the app.
 */
class DependencyInjector(applicationContext: Context) {

  val repository: Repository by lazy { provideRepository(database) }

  private val database: AppDatabase by lazy { provideDatabase(applicationContext) }
  private val dbMapper: DbMapper = DbMapperImpl()

  private fun provideDatabase(applicationContext: Context): AppDatabase =
    Room.databaseBuilder(
      applicationContext,
      AppDatabase::class.java,
      AppDatabase.DATABASE_NAME
    ).build()

  private fun provideRepository(database: AppDatabase): Repository {
    val postDao = database.postDao()

    return RepositoryImpl(postDao, dbMapper)
  }
}