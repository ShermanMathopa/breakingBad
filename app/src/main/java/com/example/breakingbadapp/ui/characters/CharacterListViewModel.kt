package com.example.breakingbadapp.ui.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.breakingbadapp.data.repository.CharacterRepository


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

