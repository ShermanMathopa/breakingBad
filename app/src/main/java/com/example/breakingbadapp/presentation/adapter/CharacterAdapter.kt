package com.example.breakingbadapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingbadapp.R
import com.example.breakingbadapp.framework.data.CharactersModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

class CharacterAdapter(private val characters: List<CharactersModel>, val action: ListAction) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.character_name)
        private val characterImage: ImageView = itemView.findViewById(R.id.characterImage)
        private val characterNickname: TextView = itemView.findViewById(R.id.character_nickname)
        private val characterDob: TextView = itemView.findViewById(R.id.character_dob)

        @SuppressLint("SetTextI18n")
        fun bindCharacter(character: CharactersModel) {
            name.text = character.name
            characterNickname.text = character.nickname
            if (character.dateOfBirth == "Unknown") {
                characterDob.text = character.dateOfBirth
            } else {
                characterDob.text =
                    "${character.dateOfBirth} (${calculateAge(character.dateOfBirth!!)})"
            }
            Glide.with(itemView.context)
                .load(character.characterImageUri)
                .into(characterImage)
            itemView.setOnClickListener {
                action.onClick(
                    character.id,
                    character.nickname!!,
                    character.dateOfBirth, character.characterImageUri!!, character.portrayed!!
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.character_item_view, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return characters.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.bindCharacter(character)
    }

}

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
