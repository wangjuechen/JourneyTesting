package com.jc.android.journeytesting.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "Posts")
@JsonClass(generateAdapter = true)
data class Post(
    @Json(name = "userId") val userId: String,
    @Json(name = "id") @PrimaryKey val id: String,
    @Json(name = "title") val title: String?,
    @Json(name = "body") val body: String?
)
