package com.jc.android.journeytesting.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "Comments")
@JsonClass(generateAdapter = true)
data class Comment(
    @Json(name = "postId") val postId: String,
    @Json(name = "id") @PrimaryKey val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String?,
    @Json(name = "body") val body: String?,
)