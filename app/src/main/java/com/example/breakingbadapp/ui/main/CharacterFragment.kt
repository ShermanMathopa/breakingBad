package com.example.breakingbadapp.ui.main

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getLong("char_id")
        val nickname = arguments?.getString("nickname")
        val dob = arguments?.getString("dateOfBirth")
        val img = arguments?.getString("img")
        //val occupation = arguments?.getString("occupation")
        val portrayed = arguments?.getString("portrayed")

        character_name.text = nickname
        character_nickname.text = dob
        character_potrayed.text = portrayed
        Glide.with(context)
            .load(img)
            .into(character_full_image)
    }
}
