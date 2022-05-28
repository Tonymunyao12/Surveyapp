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
