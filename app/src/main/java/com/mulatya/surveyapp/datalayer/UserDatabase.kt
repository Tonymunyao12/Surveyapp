/*
* Copyright 2020 BlessedCoders
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
* */

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