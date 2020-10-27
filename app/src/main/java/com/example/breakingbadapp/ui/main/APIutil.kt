package com.example.breakingbadapp.ui.main

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException

object APIutil {
           val characters = ArrayList<Character>()


    fun getCharacters(complete: (Boolean) -> Unit) {

        val charactersRequest = object : JsonArrayRequest(Method.GET, GET_CHARACTERS, null, Response.Listener { response ->
            try {
                for (x in 0 until response.length()){
                    val character = response.getJSONObject(x)
                    val name = character.getString("name")
                    val characterImage = character.getString("characterImage")

                    val newCharacter = Character(name, characterImage)
                    this.characters.add(newCharacter)
                }
                complete(true)
            } catch (e: JSONException) {
                Log.d("error", "errors")
                complete(false)
            }
        }, Response.ErrorListener {
            Log.d("error", "errors")
            complete(false)
        }) {
            override fun getBodyContentType(): String {
                return "application/json: charset-utf-8"
            }
        }
    }
}