package com.example.breakingbadapp.ui.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbadapp.R
import com.example.breakingbadapp.databinding.CharactersFragmentBinding
import com.example.breakingbadapp.utils.Resource
import com.example.breakingbadapp.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterListFragment : Fragment(),
    CharacterAdapter.CharacterItemListener {

    private var binding: CharactersFragmentBinding by autoCleared()
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
    }

    private fun observe() {
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))

                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = CharacterAdapter(this)
        binding.characterListView.layoutManager = LinearLayoutManager(requireContext())
        binding.characterListView.addItemDecoration(DividerItemDecoration(context, 1))
        binding.characterListView.adapter = adapter
    }

    override fun onClickedCharacter(characterId: Long) {
        findNavController().navigate(
            R.id.action_mainFragment_to_characterFragment,
            bundleOf("char_id" to characterId)
        )
    }
}
