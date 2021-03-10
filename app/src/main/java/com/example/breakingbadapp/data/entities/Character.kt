package com.example.breakingbadapp.data.entities

import androidx.room.*

class Converters {
        @TypeConverter
        fun toListOfStrings(flatStringList: String): List<String> {
                return flatStringList.split(",")
        }
        @TypeConverter
        fun fromListOfStrings(listOfString: List<String>): String {
                return listOfString.joinToString(",")
        }
//        @TypeConverter
//        fun toListOfInt(flatIntList: Int): List<Int> {
//                return flatIntList
//        }
//        @TypeConverter
//        fun fromListOfStrings(listOfString: List<String>): String {
//                return listOfString.joinToString(",")
//        }
}


@Entity(tableName = "characters")
@TypeConverters(Converters::class)
data class Character(
        @PrimaryKey
        var id: Long?,
        var name: String?,
        var birthday: String?,
        var occupation: List<String>? = null,
        var img: String?,
        var status: String?,
        var nickname: String?,
        @Ignore
        var appearance: List<Int>? = null,
        var portrayed: String?,
        var category: String?
        )
       {
        constructor():this(id = null, name = null, birthday = null, occupation = null, img = null, status = null, nickname = null, appearance = null, portrayed = null, category = null) }

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
