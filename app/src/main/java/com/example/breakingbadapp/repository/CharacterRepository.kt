package com.example.breakingbadapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.breakingbadapp.framework.data.CharactersModel
import com.example.breakingbadapp.framework.retrofit.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val webservice: WebService) {

    fun getCharacters(): LiveData<List<CharactersModel>> {
        val data = MutableLiveData<List<CharactersModel>>()
        webservice.getCharacters().enqueue(object : Callback<List<CharactersModel>> {
            override fun onResponse(
                call: Call<List<CharactersModel>>,
                response: Response<List<CharactersModel>>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<CharactersModel>>, t: Throwable) {
                Log.d("Error", "Failed to load characters")
            }
        })
        return data
    }
    fun getCharacter(id : Long) {

    }
}