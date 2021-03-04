package com.example.breakingbadapp.data.remote


import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val characterService: CharacterService
): BaseDataSource() {

     suspend fun getCharacters() = getResult {characterService.getAllCharacters() }
     suspend fun getCharacter(id : Long) = getResult { characterService.getCharacter(id) }
}