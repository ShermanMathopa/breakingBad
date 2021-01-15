package com.example.breakingbadapp.framework.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharactersModel (
        @Expose
        @SerializedName("char_id")
        var id: Long = 0,
        @Expose
        @SerializedName("name")
        val name : String,
        @Expose
        @SerializedName("birthday")
        val dateOfBirth: String,
        @Expose
        @SerializedName("img")
        val characterImageUri: String,
        @Expose
        @SerializedName("nickname")
        val nickname: String
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
