package com.example.breakingbadapp.data.repository

import com.example.breakingbadapp.data.local.CharacterDao
import com.example.breakingbadapp.utils.performGetOperation
import com.example.breakingbadapp.data.remote.CharacterRemoteDataSource
import javax.inject.Inject

class CharacterRepository @Inject constructor(
  private val remoteDataSource: CharacterRemoteDataSource,
  private val localDataSource : CharacterDao
) {
    fun getCharacter(id: Long) = performGetOperation(
        databaseQuery = {localDataSource.getCharacter(id)},
        networkCall = {remoteDataSource.getCharacter(id)},
        saveCallResult = {localDataSource.insert(it)}
    )

    fun getCharacters() = performGetOperation(
        databaseQuery = {localDataSource.getAllCharacters()},
        networkCall = {remoteDataSource.getCharacters()},
        saveCallResult = {localDataSource.insertAll(it.results)}
    )

}

//    fun getCharacters(): LiveData<List<CharactersModel>> {
//        val data = MutableLiveData<List<CharactersModel>>()
//        webservice.getCharacters().enqueue(object : Callback<List<CharactersModel>> {
//            override fun onResponse(
//                call: Call<List<CharactersModel>>,
//                response: Response<List<CharactersModel>>
//            ) {
//                data.value = response.body()
//            }
//
//            override fun onFailure(call: Call<List<CharactersModel>>, t: Throwable) {
//                Log.d("Error", "Failed to load characters")
//            }
//        })
//        return data
//    }

//    fun getCharacter(id : Long): LiveData<List<CharactersModel>> {
//        val characterData = MutableLiveData<List<CharactersModel>>()
//        webservice.getCharacter(id).enqueue(object : Callback<List<CharactersModel>> {
//            override fun onFailure(call: Call<List<CharactersModel>>, t: Throwable) {
//                Log.d("Error", "Failed to load character ${t.message}" )
//            }
//
//            override fun onResponse(
//                call: Call<List<CharactersModel>>,
//                response: Response<List<CharactersModel>>
//            ) {
//                Log.d("Error", response.body().toString())
//                characterData.value = response.body()
//            }
//
//
//        })
//        return characterData
//    }
