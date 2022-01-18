package com.example.roomdb.room

import androidx.room.*

@Dao
interface MovieDao {

    @Insert
    suspend fun addmovie(movie: movie)

    @Update
    suspend fun updatemovie(movie: movie)

    @Delete
    suspend fun deletemovie(movie: movie)

    @Query ("SELECT * FROM movie")
    suspend fun getmovies(): List<movie>

}