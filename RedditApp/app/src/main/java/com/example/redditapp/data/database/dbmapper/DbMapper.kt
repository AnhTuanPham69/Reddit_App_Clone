package com.example.redditapp.data.database.dbmapper

import com.example.redditapp.data.database.model.PostDbModel
import com.example.redditapp.domain.model.PostModel

interface DbMapper {

  fun mapPost(dbPostDbModel: PostDbModel): PostModel

  fun mapDbPost(postModel: PostModel): PostDbModel
}