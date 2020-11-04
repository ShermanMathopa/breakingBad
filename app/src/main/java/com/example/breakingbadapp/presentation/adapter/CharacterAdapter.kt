package com.example.breakingbadapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingbadapp.R
import com.example.breakingbadapp.framework.data.Character

class CharacterAdapter(val characters: ArrayList<Character>, val action: ListAction): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.character_name)
        val characterImage = itemView.findViewById<ImageView>(R.id.characterImage)
        val characterNickname = itemView.findViewById<TextView>(R.id.character_nickname)
        val character_dob = itemView.findViewById<TextView>(R.id.character_dob)


        fun bindCharacter(character: Character) {
            name.text = character.name
            characterNickname.text = character.nickname
            character_dob.text = "${character.dateOfBirth} (age)"

            Glide.with(itemView.context)
                .load(character.characterImageUri)
                .into(characterImage)

            itemView.setOnClickListener { action.onClick(character.id) }


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