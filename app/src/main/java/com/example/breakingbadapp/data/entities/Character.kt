package com.example.breakingbadapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "characters")
data class Character(
        @PrimaryKey
        val id: Long,
        val name: String,
        val dateOfBirth: String,
        val characterImageUri: String,
        val nickname: String,
        val portrayed: String
        //val occupation: List<String>? = null

)
//    {"char_id":1,"name":"Walter White",
//        "birthday":"09-07-1958",
//        "occupation":["High School Chemistry Teacher","Meth King Pin"],
//        "img":"https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg",
//        "status":"Presumed dead",
//        "nickname":"Heisenberg",
//        "appearance":[1,2,3,4,5],
//        "portrayed":"Bryan Cranston",
//        "category":"Breaking Bad","
//        better_call_saul_appearance":[]},
