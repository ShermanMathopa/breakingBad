package com.example.breakingbadapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.breakingbadapp.ui.main.*
import kotlinx.android.synthetic.main.main_activity.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    var characters = ArrayList<Character>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow()
//        }
        getData()
    }

    private fun getData() {
        val charactersRequest =
            JsonArrayRequest(
                Request.Method.GET,
                GET_CHARACTERS,
                null,
                Response.Listener { response ->
                    try {
                        for (x in 0 until response.length()) {
                            val character = response.getJSONObject(x)
                            val name = character.getString("name")
                            val newCharacter = Character(name)
                            this.characters.add(newCharacter)
                        }

                    } catch (e: JSONException) {
                        Log.d("error", "errors")

                    }

                    val adapter = CharacterAdapter(this, characters)
                    val layout = LinearLayoutManager(this)
                    character_list_view.layoutManager = layout
                    character_list_view.addItemDecoration(DividerItemDecoration(this, 1))
                    character_list_view.adapter = adapter

                },
                Response.ErrorListener {
                    Log.d("error", "errors")


                })
        val queue = Volley.newRequestQueue(this)
        queue.add(charactersRequest)

    }
}
