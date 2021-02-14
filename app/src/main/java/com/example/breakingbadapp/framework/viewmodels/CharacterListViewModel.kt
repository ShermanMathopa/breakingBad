package com.example.breakingbadapp.framework.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.breakingbadapp.DateTimeUtils
import com.example.breakingbadapp.framework.data.CharactersModel
import com.example.breakingbadapp.repository.CharacterRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class CharacterListViewModel @ViewModelInject constructor(characterRepository: CharacterRepository) :
    ViewModel() {

    val characters: LiveData<List<CharacterViewData>> =
        Transformations.map(characterRepository.getCharacters()) {
            it.map { character ->
                CharacterViewData.from(character)
            }
        }
}

data class CharacterViewData(
    val id: Long,
    val name: String?,
    val nickName: String?,
    val dob: String,
    val imageUrl: String?,
    val occupation:  List<String>?,
    val portrayed: String?
) {

    companion object {
        fun from(character: CharactersModel): CharacterViewData {
            val dob = calculateDob(character.dateOfBirth)
            return CharacterViewData(
                character.id,
                character.name,
                character.nickname,
                dob,
                character.characterImageUri,
                character.occupation,
                character.portrayed
            )
        }

        private fun calculateDob(dob: String): String {
            if (dob == "Unknown") {
                return dob
            }
            val getBirthdayLocalDate = LocalDate.parse(dob, DateTimeFormatter.ofPattern("MM-dd-yyyy") )
            return "$dob (${DateTimeUtils.getYearsSince(getBirthdayLocalDate)})"
        }

    }
}