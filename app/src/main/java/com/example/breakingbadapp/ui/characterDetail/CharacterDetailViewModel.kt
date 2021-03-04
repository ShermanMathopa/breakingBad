package com.example.breakingbadapp.ui.characterDetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.breakingbadapp.data.entities.Character
import com.example.breakingbadapp.data.repository.CharacterRepository
import com.example.breakingbadapp.utils.Resource

class CharacterViewModel @ViewModelInject constructor(
    private val characterRepository: CharacterRepository): ViewModel() {

    private val _id = MutableLiveData<Long>()

    private val _character = _id.switchMap {
        characterRepository.getCharacter(it)
    }
    val character : LiveData<Resource<Character>> = _character

    fun start(id: Long) {
      _id.value = 1
    }

   //val character: LiveData<List<CharactersModel>> = characterRepository.getCharacter(1)
//    val characters: LiveData<List<CharacterView>> =
//        Transformations.map(characterRepository.getCharacter(1)) {
//            it.map { character ->
//                CharacterView.from(
//                    character
//                )
//            }
//        }

}


