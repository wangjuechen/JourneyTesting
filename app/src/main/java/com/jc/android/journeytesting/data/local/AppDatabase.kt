package com.jc.android.journeytesting.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jc.android.journeytesting.domain.Comment
import com.jc.android.journeytesting.domain.Post

@Database(
    entities = [Post::class, Comment::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase()  {

    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao


    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(
                    context
                ).also { instance = it }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "Posts")
                .fallbackToDestructiveMigration()
                .build()
    }
}