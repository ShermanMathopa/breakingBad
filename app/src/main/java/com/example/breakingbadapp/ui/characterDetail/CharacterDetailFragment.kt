package com.example.breakingbadapp.ui.characterDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.breakingbadapp.data.entities.Character
import com.example.breakingbadapp.databinding.CharacterDetailFragmentBinding
import com.example.breakingbadapp.utils.DateTimeUtils
import com.example.breakingbadapp.utils.Resource
import com.example.breakingbadapp.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter


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
                    Timber.d(it.message)

                Resource.Status.LOADING -> {
                    //binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun bindCharacter(character: Character) {
        binding.characterDetailNickname.text = character.nickname
        if (character.birthday == "Unknown") {
            binding.characterDetailDob.text = character.birthday
        } else {
            val getBirthdayToLocalDate = LocalDate.parse(
               character.birthday,
                DateTimeFormatter.ofPattern("MM-dd-yyyy")
            )
            binding.characterDetailDob.text =
                "${character.birthday} (${DateTimeUtils.getYearsSince(getBirthdayToLocalDate)})"
        }
        binding.characterDetailOccupation.text = character.occupation?.toList().toString()
        binding.characterDetalPotrayed.text = character.portrayed
        Glide.with(context)
            .load(character.img)
            .into(binding.image)
    }
}
