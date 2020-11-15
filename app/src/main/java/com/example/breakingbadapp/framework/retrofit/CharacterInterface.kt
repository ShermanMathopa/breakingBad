package com.example.breakingbadapp.framework.retrofit

import com.example.breakingbadapp.framework.data.CharacterModel
import retrofit2.Call
import retrofit2.http.GET

interface CharacterInterface {
    @GET("characters")
    fun getCharacters() : Call<List<CharacterModel>>
}