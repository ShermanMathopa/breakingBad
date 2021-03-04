package com.example.breakingbadapp.ui.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbadapp.R
import com.example.breakingbadapp.databinding.CharactersFragmentBinding
import com.example.breakingbadapp.utils.Resource
import com.example.breakingbadapp.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.characters_fragment.*



@AndroidEntryPoint
class CharacterListFragment : Fragment(),
    ListAction {

    private var binding: CharactersFragmentBinding by autoCleared()
   // private lateinit var progressBar: ProgressBar
    private val viewModel: CharacterListViewModel by viewModels()
    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharactersFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       setupRecyclerView()
       observe()
//
//        viewModel.characters.observe(viewLifecycleOwner) { characters ->
//            character_list_view.adapter =
//                CharacterAdapter(
//                    characters,
//                    this
//                )
//            character_list_view.layoutManager =
//                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            character_list_view.addItemDecoration(DividerItemDecoration(context, 1))
//            progressDialog.visibility = View.GONE
//        }

    }

    private fun observe() {
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            when (it.status){
                Resource.Status.SUCCESS -> {
                    //progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))

                }
                Resource.Status.ERROR ->
                    //Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    Log.d("Sherman", it.message)

                Resource.Status.LOADING ->
                    Toast.makeText(requireContext(), "failed to load", Toast.LENGTH_SHORT).show()

                //  progressBar.visibility = View.VISIBLE

            }
        })
    }

    private fun setupRecyclerView() {
       adapter = CharacterAdapter(this)
       binding.characterListView.layoutManager = LinearLayoutManager(requireContext())
       binding.characterListView.addItemDecoration(DividerItemDecoration(context, 1))
       binding.characterListView.adapter = adapter
    }

    private fun goToCharacterDetails(id: Long) {

        //TODO pass in characterViewData - find out how to do it in nav graph
       // val action = CharacterListFragmentDirections.actionMainFragmentToCharacterFragment(id)
       /// Navigation.findNavController(character_list_view).navigate(action)
        findNavController().navigate(
            R.id.action_mainFragment_to_characterFragment,
            bundleOf("char_id" to id)
        )

    }

    override fun onClick(id : Long) {
        goToCharacterDetails(id)
    }

}
