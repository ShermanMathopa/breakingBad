package com.example.breakingbadapp.ui.characterDetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.breakingbadapp.data.entities.Character
import com.example.breakingbadapp.data.repository.CharacterRepository
import com.example.breakingbadapp.utils.Resource

class CharacterDetailViewModel @ViewModelInject constructor(
    private val characterRepository: CharacterRepository
): ViewModel() {

    private val _id = MutableLiveData<Long>()

    private val _character = _id.switchMap {
        characterRepository.getCharacter(it)
    }
    val character : LiveData<Resource<Character>> = _character

    fun start(id: Long) {
        _id.value = id
    }

}