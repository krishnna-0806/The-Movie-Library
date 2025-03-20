package com.example.themovielibrary.home.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themovielibrary.home.api.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    /*companion object {
    @Volatile
    private var INSTANCE: MovieDatabase? = null

    fun getDatabase(): MovieDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                MovieApplication.applicationContext(),
                MovieDatabase::class.java,
                "movies"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}*/
}


