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

class CharacterAdapter(val characters: List<CharactersModel>, val action: ListAction): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById<TextView>(R.id.character_name)
        val characterImage: ImageView = itemView.findViewById<ImageView>(R.id.characterImage)
        val characterNickname: TextView = itemView.findViewById<TextView>(R.id.character_nickname)
        val character_dob: TextView = itemView.findViewById<TextView>(R.id.character_dob)


        @SuppressLint("SetTextI18n")
        fun bindCharacter(character: CharactersModel) {
            name.text = character.name
            characterNickname.text = character.nickname

            if (character.dateOfBirth == "Unknown") {
                character_dob.text = character.dateOfBirth
            } else {
                character_dob.text = "${character.dateOfBirth} (${calculateAge(character.dateOfBirth!!)})"
            }
            Glide.with(itemView.context)
                .load(character.characterImageUri)
                .into(characterImage)

            itemView.setOnClickListener { action.onClick(character.id,
                character.nickname!!,
                character.dateOfBirth!!, character.characterImageUri!!, character.portrayed!! ) }


            //age- formatted
            // dateOfBirth.text = "\(character.dob) (\(character.age))" 1942-02-02 (45)
            // dateOfBirth.text = character.birth
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
private fun calculateAge(dob : String) : Int{
    val simpleDateFormat = SimpleDateFormat("MM-dd-yyyy")
    val date1 = simpleDateFormat.parse(dob)

    val calendar = Calendar.getInstance()
    calendar.time = date1
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH) + 1
    val date: Int = calendar.get(Calendar.DATE)
    val l1: LocalDate = LocalDate.of(year,month, date)
    val now1: LocalDate = LocalDate.now()
    val diff1: Period = Period.between(l1, now1)

    return diff1.years
}
