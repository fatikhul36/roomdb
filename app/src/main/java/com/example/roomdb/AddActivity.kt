package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomdb.room.movie
import com.example.roomdb.room.movieDb
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    val db by lazy { movieDb(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setupListener()
    }

    fun setupListener() {
        btn_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                db.moviedao().addmovie(
                    movie(0,AddMovieTitle.text.toString(),AddMovieDesc.text.toString())
                )

                finish()
            }
        }
    }
}