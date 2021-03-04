package com.example.breakingbadapp.ui.characterDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.breakingbadapp.data.entities.Character
import com.example.breakingbadapp.databinding.CharacterDetailFragmentBinding
import com.example.breakingbadapp.utils.Resource
import com.example.breakingbadapp.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var binding : CharacterDetailFragmentBinding by autoCleared()
    private val viewModel: CharacterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getLong("char_id")?.let { viewModel.start(it) }

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



private fun bindCharacter(character : Character) {
    binding.characterDateOfBirth.text = character.dateOfBirth
    binding.characterPotrayed.text = character.portrayed
    binding.nickName.text = character.nickname
//    character_dateOfBirth.text = arguments?.getString("dateOfBirth")
//    character_potrayed.text = arguments?.getString("portrayed")
//    Glide.with(context)
//        .load(arguments?.getString("img"))
//        .into(character_full_image)
//    character_occupation.text = arguments?.getStringArray("occupation")?.toList().toString()

}

}
