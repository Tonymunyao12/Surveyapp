package com.mulatya.surveyapp.datalayer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_Details_table")
data class UserEntity(

   @PrimaryKey(autoGenerate = true)
   var userId: Int = 0,
)
