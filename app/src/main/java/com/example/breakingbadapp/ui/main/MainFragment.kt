package com.example.breakingbadapp.ui.main

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbadapp.R
import com.example.breakingbadapp.framework.data.CharactersModel
import com.example.breakingbadapp.framework.viewmodels.MainViewModel
import com.example.breakingbadapp.presentation.adapter.CharacterAdapter
import com.example.breakingbadapp.presentation.adapter.ListAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class MainFragment : Fragment(), ListAction {

    lateinit var progerssProgressDialog: ProgressDialog

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progerssProgressDialog = ProgressDialog(activity)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()

        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            character_list_view.adapter = CharacterAdapter(characters, this)
            character_list_view.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            character_list_view.addItemDecoration(DividerItemDecoration(context, 1))
            progerssProgressDialog.dismiss()
        }

    }

    private fun goToCharacterDetails(id: Long = 0L, nickname: String, dateOfBirth: String, image: String,portrayed: String) {
        val action = MainFragmentDirections.actionMainFragmentToCharacterFragment(id, nickname, dateOfBirth, image, portrayed)
        //findNavController().navigate(action)
        Navigation.findNavController(character_list_view).navigate(action)
    }

    override fun onClick(
        id: Long,
        nickname: String,
        dateOfBirth: String,
        image: String,
        portrayed: String
    ) {
        goToCharacterDetails(id, nickname, dateOfBirth, image, portrayed)
    }

}
