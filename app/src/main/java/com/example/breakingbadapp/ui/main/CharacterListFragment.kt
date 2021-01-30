package com.example.breakingbadapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbadapp.R
import com.example.breakingbadapp.framework.viewmodels.CharacterListViewModel
import com.example.breakingbadapp.framework.viewmodels.CharacterViewData
import com.example.breakingbadapp.presentation.adapter.CharacterAdapter
import com.example.breakingbadapp.presentation.adapter.ListAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class CharacterListFragment : Fragment(), ListAction {

    private lateinit var progressDialog: ProgressBar

    private val viewModel: CharacterListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog = ProgressBar(activity)
        progressDialog.visibility = View.VISIBLE

        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            character_list_view.adapter = CharacterAdapter(characters, this)
            character_list_view.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            character_list_view.addItemDecoration(DividerItemDecoration(context, 1))
            progressDialog.visibility = View.GONE
        }

    }

    private fun goToCharacterDetails(character: CharacterViewData) {

        //TODO pass in characterViewData - find out how to do it in nav graph
        val action = CharacterListFragmentDirections.actionMainFragmentToCharacterFragment(character.id, character.dob, character.imageUrl, )
        Navigation.findNavController(character_list_view).navigate(action)
    }

    override fun onClick(character: CharacterViewData) {
        goToCharacterDetails(character)
    }

}
