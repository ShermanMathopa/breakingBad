package com.example.breakingbadapp.framework.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.breakingbadapp.framework.data.CharactersModel
import com.example.breakingbadapp.repository.CharacterRepository


class MainViewModel @ViewModelInject constructor(characterRepository: CharacterRepository): ViewModel() {

    // TODO: Implement the ViewModel
    val characters : LiveData<List<CharactersModel>> = characterRepository.getCharacters()
}
