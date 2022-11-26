package com.example.doctorapp.data

import android.content.Context
import androidx.room.*

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)

abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao?

    companion object{
        private var  INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if(INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "notes.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}