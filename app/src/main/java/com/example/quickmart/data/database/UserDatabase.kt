package com.example.quickmart.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quickmart.data.dao.UserDao
import com.example.quickmart.data.models.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE : UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return (INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                )
//                    .addMigrations(MIGRATION_1_2)
                    .build()

                INSTANCE = instance
                instance
            })
        }
    }

}

val MIGRATION_1_2 = object : Migration(1,2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE User ADD COLUMN photo BLOB")
    }

}