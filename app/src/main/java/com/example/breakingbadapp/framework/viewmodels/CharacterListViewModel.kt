package com.example.breakingbadapp.framework.viewmodels

import android.annotation.SuppressLint
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.breakingbadapp.framework.data.CharactersModel
import com.example.breakingbadapp.repository.CharacterRepository
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.*


class CharacterListViewModel @ViewModelInject constructor(characterRepository: CharacterRepository) :
    ViewModel() {

    val characters: LiveData<List<CharacterViewData>> =
        Transformations.map(characterRepository.getCharacters()) {
            it.map { character ->
                CharacterViewData.from(character)
            }
        }


}

//TODO add id and potrayed and occupation
data class CharacterViewData(
    val id: Long,
    val name: String?,
    val nickName: String?,
    val dob: String,
    val imageUrl: String?,
    val occupation: String?,
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
                character.portrayed,
                character.occupation.toString()
            )
        }

        private fun calculateDob(dob: String): String {
            if (dob == "Unknown") {
                return dob
            }
            return "$dob (${calculateAge(dob)})"
        }


        //TODO make this non-horrible!!!!
        @SuppressLint("SimpleDateFormat")
        private fun calculateAge(dob: String): Int {

            val simpleDateFormat = SimpleDateFormat("MM-dd-yyyy")
            val parseDate = simpleDateFormat.parse(dob)

            val calendar = Calendar.getInstance()
            calendar.time = parseDate!!
            val year: Int = calendar.get(Calendar.YEAR)
            val month: Int = calendar.get(Calendar.MONTH) + 1
            val date: Int = calendar.get(Calendar.DATE)
            val l1: LocalDate = LocalDate.of(year, month, date)
            val now1: LocalDate = LocalDate.now()
            val diff1: Period = Period.between(l1, now1)

            return diff1.years
        }
    }

}
