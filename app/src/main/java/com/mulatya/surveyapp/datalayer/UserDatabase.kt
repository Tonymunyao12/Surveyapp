package com.mulatya.surveyapp.datalayer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [UserEntity::class], version = 1)
 abstract class UserDatabase : RoomDatabase() {

   abstract val userDatabaseDao: UserDatabaseDao

   companion object {

    @Volatile
     private var INSTANCE: UserDatabase? = null

     fun getInstance(context: Context): UserDatabase?{
         synchronized(this) {

             var instance = INSTANCE

             if (instance == null) {
                 instance = Room.databaseBuilder(
                     context.applicationContext,
                     UserDatabase::class.java,
                     "user_details_database"
                 )
                     .fallbackToDestructiveMigration()
                     .build()


                 INSTANCE = instance
             }
             return instance
         }
     }
   }
}