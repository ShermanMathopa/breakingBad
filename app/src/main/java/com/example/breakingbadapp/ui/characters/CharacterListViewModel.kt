package com.example.breakingbadapp.ui.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.breakingbadapp.utils.DateTimeUtils
import com.example.breakingbadapp.data.entities.Character
import com.example.breakingbadapp.data.repository.CharacterRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class CharacterListViewModel @ViewModelInject constructor(characterRepository: CharacterRepository) :
    ViewModel() {

    val characters = characterRepository.getCharacters()
//
//    val characters: LiveData<List<CharacterViewData>> =
//        Transformations.map(characterRepository.getCharacters()) {
//            it.map { character ->
//                CharacterViewData.from(
//                    character
//                )
//            }
//        }
}

