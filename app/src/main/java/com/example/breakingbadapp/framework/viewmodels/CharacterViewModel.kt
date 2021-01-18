package com.example.breakingbadapp.framework.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.breakingbadapp.repository.CharacterRepository

class CharacterViewModel @ViewModelInject constructor(characterRepository: CharacterRepository): ViewModel() {
   // val characterProfile : LiveData<List<CharacterProfile>> = characterRepository.getCharacters()
    // TODO: Implement the ViewModel
}
