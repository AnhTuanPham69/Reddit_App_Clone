package com.example.redditapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.redditapp.data.database.dao.PostDao
import com.example.redditapp.data.database.dbmapper.DbMapper
import com.example.redditapp.data.database.model.PostDbModel
import com.example.redditapp.domain.model.PostModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RepositoryImpl(private val postDao: PostDao, private val mapper: DbMapper) : Repository {

  private var searchedText = ""

  private val allPostsLiveData: MutableLiveData<List<PostModel>> by lazy {
    MutableLiveData<List<PostModel>>()
  }

  private val ownedPostsLiveData: MutableLiveData<List<PostModel>> by lazy {
    MutableLiveData<List<PostModel>>()
  }

  init {
    initDatabase(this::updatePostLiveData)
  }

  /**
   * Populates database with posts if it is empty.
   */
  private fun initDatabase(postInitAction: () -> Unit) {
    GlobalScope.launch {
      // Prepopulate posts
      val posts = PostDbModel.DEFAULT_POSTS.toTypedArray()
      val dbPosts = postDao.getAllPosts()
      if (dbPosts.isNullOrEmpty()) {
        postDao.insertAll(*posts)
      }

      postInitAction.invoke()
    }
  }

  override fun getAllPosts(): LiveData<List<PostModel>> = allPostsLiveData

  override fun getAllOwnedPosts(): LiveData<List<PostModel>> = ownedPostsLiveData

  override fun getAllSubreddits(searchedText: String): List<String> {
    this.searchedText = searchedText

    if (searchedText.isNotEmpty()) {
      return postDao.getAllSubreddits().filter { it.contains(searchedText) }
    }

    return postDao.getAllSubreddits()
  }

  private fun getAllPostsFromDatabase(): List<PostModel> =
    postDao.getAllPosts().map(mapper::mapPost)

  private fun getAllOwnedPostsFromDatabase(): List<PostModel> =
    postDao.getAllOwnedPosts("anhphamtuan").map(mapper::mapPost)

  override fun insert(post: PostModel) {
    postDao.insert(mapper.mapDbPost(post))
    updatePostLiveData()
  }

  override fun deleteAll() {
    postDao.deleteAll()

    updatePostLiveData()
  }

  private fun updatePostLiveData() {
    allPostsLiveData.postValue(getAllPostsFromDatabase())
    ownedPostsLiveData.postValue(getAllOwnedPostsFromDatabase())
  }
}