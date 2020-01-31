package pe.com.peruapps.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import pe.com.peruapps.model.Post
import pe.com.peruapps.model.PostDao

@Database(entities = arrayOf(Post::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}