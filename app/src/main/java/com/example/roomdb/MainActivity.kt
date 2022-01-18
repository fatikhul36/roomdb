package com.example.roomdb

import android.content.Intent
import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.roomdb.room.movie
import com.example.roomdb.room.movieDb
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var movieAdapter : MovieAdapter

    val db by lazy { movieDb(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setuplistener()
        setupRecycleview()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch{
           val movies = db.moviedao().getmovies()
            Log.d("MainActivity","dbresponse: $movies")
            withContext(Dispatchers.Main){
                movieAdapter.setData(movies)
            }
        }
    }

    fun setuplistener(){
        addmovie.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }

    private fun setupRecycleview(){
        movieAdapter = MovieAdapter(arrayListOf())
        rv_movie.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = movieAdapter
        }
    }
}