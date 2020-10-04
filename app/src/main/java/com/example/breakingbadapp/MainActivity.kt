package com.example.breakingbadapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.breakingbadapp.ui.main.APIutil
import com.example.breakingbadapp.ui.main.Character
import com.example.breakingbadapp.ui.main.GET_CHARACTERS
import com.example.breakingbadapp.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_activity.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val queue = Volley.newRequestQueue(this)

        val charactersRequest =
            JsonArrayRequest(Request.Method.GET, GET_CHARACTERS, null, Response.Listener { response ->
                try {
                    for (x in 0 until response.length()) {
                        val character = response.getJSONObject(x)
                        val name = character.getString("name")
                        textViewName.text = name
                       // val newCharacter = Character(name)
                       // this.characters.add(newCharacter)
                    }

                } catch (e: JSONException) {
                    Log.d("error", "errors")

                }
            }, Response.ErrorListener {
                Log.d("error", "errors")
                textViewName.text = "Not"

            })
        queue.add(charactersRequest)

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow()
//        }
    }
}
