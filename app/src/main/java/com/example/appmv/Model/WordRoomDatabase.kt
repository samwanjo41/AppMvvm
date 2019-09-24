package com.example.appmv.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 2)
abstract class WordRoomDatabase : RoomDatabase(){
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        var database: WordRoomDatabase? = null

        fun getInstance(context: Context): WordRoomDatabase? {
            if(database == null){
                synchronized(WordRoomDatabase::class.java){
                    if(database == null){
                        database = Room.databaseBuilder(context.applicationContext, WordRoomDatabase::class.java, "word_table").fallbackToDestructiveMigration().build()
                    }
                }
            }
            return database
        }
    }
}