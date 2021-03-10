package com.example.breakingbadapp.ui.characterDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.breakingbadapp.data.entities.Character
import com.example.breakingbadapp.databinding.CharacterDetailFragmentBinding
import com.example.breakingbadapp.utils.Resource
import com.example.breakingbadapp.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.character_detail_fragment.*


@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private var binding: CharacterDetailFragmentBinding by autoCleared()
    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getLong("char_id")?.let {viewModel.start(it)}
        observe()
    }

    private fun observe() {
        viewModel.character.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindCharacter(it.data!!)
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {

                }
            }
        })
    }

    private fun bindCharacter(character: Character) {

        //  character_occupation.text = arguments?.getStringArray("occupation")?.toList().toString()
        binding.characterDetailNickname.text = character.nickname
        binding.characterDetailDob.text = character.birthday
        binding.characterDetailOccupation.text = character.occupation?.toList().toString()
        binding.characterDetalPotrayed.text = character.portrayed
        Glide.with(context)
            .load(character.img)
            .into(binding.image)
    }
}
