package com.example.breakingbadapp.presentation.adapter

interface ListAction {
    fun onClick(id: Long,
                nickname : String,
                dateOfBirth : String,
                image : String,
                portrayed : String
    )
}