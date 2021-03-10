package com.example.breakingbadapp.ui.characters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingbadapp.data.entities.Character
import com.example.breakingbadapp.databinding.CharacterItemViewBinding
import com.example.breakingbadapp.utils.DateTimeUtils
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CharacterAdapter(private val listener: CharacterItemListener) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private val items = ArrayList<Character>()

    fun setItems(items: ArrayList<Character>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CharacterItemViewBinding =
            CharacterItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindCharacter(items[position])


    inner class ViewHolder(
        private val itemViewBinding: CharacterItemViewBinding,
        private val listener: CharacterItemListener
    ) :
        RecyclerView.ViewHolder(itemViewBinding.root),
        View.OnClickListener {

        private lateinit var character: Character

        init {
            itemViewBinding.root.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bindCharacter(item: Character) {
            this.character = item
            itemViewBinding.CharacterName.text = item.name
            itemViewBinding.characterNickname.text = item.nickname

            if (item.birthday == "Unknown") {
                itemViewBinding.characterDobAge.text = item.birthday
            } else {
                val getBirthdayToLocalDate = LocalDate.parse(
                    item.birthday,
                    DateTimeFormatter.ofPattern("MM-dd-yyyy")
                )
                itemViewBinding.characterDobAge.text =
                    "${item.birthday} (${DateTimeUtils.getYearsSince(getBirthdayToLocalDate)})"
            }

            Glide.with(itemView.context)
                .load(item.img)
                .into(itemViewBinding.characterImage)
        }

        override fun onClick(v: View?) {
            listener.onClickedCharacter(character.id!!)
        }
    }

    interface CharacterItemListener {
        fun onClickedCharacter(characterId: Long)
    }
}