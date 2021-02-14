package com.example.breakingbadapp.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.breakingbadapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.character_fragment.*

@AndroidEntryPoint
class CharacterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.character_fragment, container, false)
    }

 //TODO search nav-graph xml parcelable
    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
     super.onActivityCreated(savedInstanceState)
     arguments?.getLong("char_id")
     nickName.text = arguments?.getString("nickname")
     character_dateOfBirth.text = arguments?.getString("dateOfBirth")
     character_potrayed.text = arguments?.getString("portrayed")
     Glide.with(context)
         .load(arguments?.getString("img"))
         .into(character_full_image)
     character_occupation.text = arguments?.getStringArray("occupation")?.toList().toString()
    }
}
