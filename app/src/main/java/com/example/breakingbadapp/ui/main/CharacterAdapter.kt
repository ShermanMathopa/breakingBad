package com.example.breakingbadapp.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingbadapp.R

class CharacterAdapter(val context: Context, val characters: ArrayList<Character>): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.character_name)
        val characterImage = itemView.findViewById<ImageView>(R.id.characterImage)

        fun bindCharacter(character: Character) {
            name.text = character.name

            Glide.with(itemView.context)
                .load(character.characterImage)
                .into(characterImage)


            //age- formatted
            // dateOfBirth.text = "\(character.dob) (\(character.age))" 1942-02-02 (45)
            // dateOfBirth.text = character.birth
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapter.ViewHolder {
        val layoutInflater =
            LayoutInflater.from(context).inflate(R.layout.character_item_view, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return characters.count()
    }

    override fun onBindViewHolder(holder: CharacterAdapter.ViewHolder, position: Int) {
        val character = characters[position]
        holder.bindCharacter(character)
    }

}