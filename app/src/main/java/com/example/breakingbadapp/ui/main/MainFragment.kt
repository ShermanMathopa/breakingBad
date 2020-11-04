package com.example.breakingbadapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.breakingbadapp.R
import com.example.breakingbadapp.framework.data.Character
import com.example.breakingbadapp.framework.data.GET_CHARACTERS
import com.example.breakingbadapp.framework.viewmodels.MainViewModel
import com.example.breakingbadapp.presentation.adapter.CharacterAdapter
import com.example.breakingbadapp.presentation.adapter.ListAction
import kotlinx.android.synthetic.main.main_fragment.*
import org.json.JSONException

class MainFragment : Fragment(), ListAction {
    var characters = ArrayList<Character>()
    private var characterAdapter =
        CharacterAdapter(
           characters, this
        )



    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }



    private fun goToCharacterDetails(id : Long = 0L) {
        val action = MainFragmentDirections.actionMainFragmentToCharacterFragment(id)
            //findNavController().navigate(action)
           Navigation.findNavController(character_list_view).navigate(action)
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
                            val id = character.getLong("char_id")
                            val name = character.getString("name")
                            val dateOfBirth = character.getString("birthday")
                          //  val occupation = character.getString("occupation").toString()
                            val characterImageUri = character.getString("img")
                            val nickname = character.getString("nickname")
                           // val portrayed = character.getString("portrayed")
                            val newCharacter =
                                Character(
                                    id, name, dateOfBirth,  characterImageUri, nickname

                                )
                            this.characters.add(newCharacter)
                        }

                    } catch (e: JSONException) {
                        Log.d("error", "errors")

                    }
                    val adapter =
                        CharacterAdapter(
                            characters, this
                        )


                    val layout = LinearLayoutManager(context)
                    character_list_view.layoutManager = layout
                    character_list_view.addItemDecoration(DividerItemDecoration(context, 1))
                    character_list_view.adapter = adapter

//                    character_list_view.apply {
//                        layoutManager = LinearLayoutManager(context)
//                       addItemDecoration(DividerItemDecoration(context, 1))
//                       adapter = characterAdapter
//                    }



                },
                Response.ErrorListener {
                    Log.d("error", "errors")


                })
        val queue = Volley.newRequestQueue(context)
        queue.add(charactersRequest)

    }

    override fun onClick(id: Long) {
      goToCharacterDetails(id)
    }

}
