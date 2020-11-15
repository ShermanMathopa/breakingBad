package com.example.breakingbadapp.ui.main

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbadapp.R
import com.example.breakingbadapp.framework.data.CharacterModel
import com.example.breakingbadapp.framework.retrofit.ApiClient
import com.example.breakingbadapp.framework.viewmodels.MainViewModel
import com.example.breakingbadapp.presentation.adapter.CharacterAdapter
import com.example.breakingbadapp.presentation.adapter.ListAction
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Call
import retrofit2.Callback

class MainFragment : Fragment(), ListAction {
    var characters = ArrayList<CharacterModel>()
    lateinit var progerssProgressDialog: ProgressDialog


    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        character_list_view.adapter = CharacterAdapter(characters, this)
        character_list_view.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        character_list_view.addItemDecoration(DividerItemDecoration(context, 1))

        progerssProgressDialog = ProgressDialog(activity)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        getData()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }


    private fun goToCharacterDetails(id: Long = 0L) {
        val action = MainFragmentDirections.actionMainFragmentToCharacterFragment(id)
        //findNavController().navigate(action)
        Navigation.findNavController(character_list_view).navigate(action)
    }

    private fun getData() {
        val call: Call<List<CharacterModel>> = ApiClient.getClient.getCharacters()
        call.enqueue(object : Callback<List<CharacterModel>> {

            override fun onFailure(call: Call<List<CharacterModel>>?, t: Throwable?) {
                progerssProgressDialog.dismiss()
            }

            override fun onResponse(
                call: Call<List<CharacterModel>>, response: retrofit2.Response<List<CharacterModel>>
            ) {
                progerssProgressDialog.dismiss()
                characters.addAll(response.body()!!)
                character_list_view.adapter?.notifyDataSetChanged()

            }

        })
    }

    override fun onClick(id: Long) {
        goToCharacterDetails(id)
    }

}
