package com.example.breakingbadapp.data.remote


import com.example.breakingbadapp.data.entities.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface CharacterService {

    @GET("characters")
    suspend fun getAllCharacters() : Response<List<Character>>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Long): Response<Character>
}