package com.example.breakingbadapp.framework.retrofit

import com.example.breakingbadapp.framework.data.CharactersModel
import dagger.Provides
import retrofit2.Call
import retrofit2.http.GET


interface WebService {
    @GET("characters")
    fun getCharacters() : Call<List<CharactersModel>>
}