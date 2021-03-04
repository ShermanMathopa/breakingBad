package com.example.breakingbadapp.ui.characters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingbadapp.R
import com.example.breakingbadapp.data.entities.Character
import com.example.breakingbadapp.databinding.CharacterItemViewBinding

class CharacterAdapter(private val action: ListAction) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private val items = ArrayList<Character>()

    fun setItems(items: ArrayList<Character>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CharacterItemViewBinding = CharacterItemViewBinding.inflate(LayoutInflater.from(parent.context),parent, false)
//        val layoutInflater =
//            LayoutInflater.from(parent.context).inflate(R.layout.character_item_view, parent, false)
      return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindCharacter(items[position])



    inner class ViewHolder(private val itemViewBinding: CharacterItemViewBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {

        private lateinit var character: Character


//        private val name: TextView = itemView.findViewById(R.id.nickName)
//        private val characterImage: ImageView = itemView.findViewById(R.id.characterImage)
//        private val characterNickname: TextView = itemView.findViewById(R.id.character_dateOfBirth)
//        private val characterDob: TextView = itemView.findViewById(R.id.character_occupation)

        @SuppressLint("SetTextI18n")
        fun bindCharacter(item: Character) {
            this.character = item
            itemViewBinding.nickName.text = item.name
            itemViewBinding.characterDateOfBirth.text = item.dateOfBirth

//            Glide.with(itemViewBinding.root)
//                .load(item.characterImageUri)
//                .into(itemViewBinding.characterImage)

            Glide.with(itemView.context)
                .load(item.characterImageUri)
                .into(itemViewBinding.characterImage)

            itemView.setOnClickListener {
                action.onClick(
                    character.id
                //TODO change onclick to handle characterViewData
                )
            }
        }

    }


}