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
import com.example.breakingbadapp.framework.viewmodels.CharacterViewData

class CharacterAdapter(private val characters: List<CharacterViewData>, val action: ListAction) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.nickName)
        private val characterImage: ImageView = itemView.findViewById(R.id.characterImage)
        private val characterNickname: TextView = itemView.findViewById(R.id.character_dateOfBirth)
        private val characterDob: TextView = itemView.findViewById(R.id.character_occupation)

        @SuppressLint("SetTextI18n")
        fun bindCharacter(character: CharacterViewData) {
            name.text = character.name
            characterNickname.text = character.nickName
            characterDob.text = character.dob
            Glide.with(itemView.context)
                .load(character.imageUrl)
                .into(characterImage)

            itemView.setOnClickListener {
                action.onClick(
                    character.id,
                    character.nickName!!,
                    character.dob,
                    character.imageUrl!!,
                    character.portrayed!!,
                    character.occupation!!
                //TODO change onclick to handle characterViewData
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