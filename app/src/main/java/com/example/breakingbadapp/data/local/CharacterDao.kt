package com.example.breakingbadapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.breakingbadapp.data.entities.Character


@Dao
interface CharacterDao {

      @Query("SELECT * FROM characters")
      fun getAllCharacters() : LiveData<List<Character>>

      @Query("SELECT * FROM characters WHERE id = :id")
      fun getCharacter(id: Long): LiveData<Character>

      @Insert(onConflict = OnConflictStrategy.REPLACE)
      suspend fun insertAll(characters: List<Character>)

      @Insert(onConflict = OnConflictStrategy.REPLACE)
      suspend fun insert(character: Character)

}