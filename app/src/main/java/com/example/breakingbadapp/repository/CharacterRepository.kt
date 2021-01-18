package com.example.breakingbadapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.breakingbadapp.framework.data.CharactersModel
import com.example.breakingbadapp.framework.retrofit.AnalyticsModule
import com.example.breakingbadapp.framework.retrofit.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor( private val webservice: WebService) {

   // ...
   fun getCharacters(): LiveData<List<CharactersModel>> {
       // This isn't an optimal implementation. We'll fix it later.
       val data = MutableLiveData<List<CharactersModel>>()
       //ApiClient.getClient
       AnalyticsModule.provideAnalyticsService().getCharacters().enqueue(object : Callback<List<CharactersModel>> {
           override fun onResponse(call: Call<List<CharactersModel>>, response: Response<List<CharactersModel>>) {
               data.value = response.body()
           }
           // Error case is left out for brevity.
           override fun onFailure(call: Call<List<CharactersModel>>, t: Throwable) {
               Log.d("Error", "Failed to load characters")
           }
       })
       return data
   }
}