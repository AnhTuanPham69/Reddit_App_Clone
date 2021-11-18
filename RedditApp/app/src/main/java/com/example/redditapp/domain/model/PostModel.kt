package com.example.redditapp.domain.model

import com.example.redditapp.R

data class PostModel(
  val username: String,
  val subreddit: String,
  val title: String,
  val text: String,
  val likes: String,
  val comments: String,
  val type: PostType,
  val postedTime: String,
  val image: Int?
) {

  companion object {

    val DEFAULT_POST = PostModel(
      "anhphamtuan",
      "androiddev",
      "Watch this awesome Jetpack Compose course!",
      "",
      "5614",
      "523",
      PostType.IMAGE,
      "4h",
      R.drawable.compose_course
    )

    val EMPTY = PostModel(
      "anhphamtuan",
      "anhphamtuan.com",
      "",
      "",
      "0",
      "0",
      PostType.TEXT,
      "0h",
      R.drawable.compose_course
    )
  }
}