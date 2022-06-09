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

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_Details_table")
data class UserEntity(

   @PrimaryKey(autoGenerate = true)
   var userId: Int = 0,

   @ColumnInfo(name = "full_name")
   var fullName: String,

   @ColumnInfo(name = "phone_number")
   var phoneNumber: Int,

   @ColumnInfo(name = "gender")
   var gender: String,

   @ColumnInfo(name = "size_of_farm")
   var farmSize: Float,

)
