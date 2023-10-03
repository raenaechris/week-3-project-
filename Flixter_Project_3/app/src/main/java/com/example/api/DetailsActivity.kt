package com.example.api

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailsActivity {
    // Main Activity
    class MainActivity : AppCompatActivity() {

        companion object {
            const val KEY_MOVIE_TITLE = "movie_title"
            const val KEY_MOVIE_RELEASE_DATE = "movie_release_date"
            const val KEY_MOVIE_OVERVIEW = "movie_overview"
            const val KEY_MOVIE_POSTER_URL = "movie_poster_url"
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            // set up click listener for movie item
            val movieItem = findViewById<View>(R.id.recyclerViewMovies)
            movieItem.setOnClickListener {
                // create intent to start detail activity
                val intent = Intent(this, DetailActivity::class.java).apply {
                    // add data for movie title, release date, overview, and poster URL
                    putExtra(KEY_MOVIE_TITLE, "The Matrix")
                    putExtra(KEY_MOVIE_RELEASE_DATE, "March 31, 1999")
                    putExtra(KEY_MOVIE_OVERVIEW, "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.")
                    putExtra(KEY_MOVIE_POSTER_URL, "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg")
                }
                startActivity(intent)
            }
        }
    }

    // Detail Activity
    class DetailActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.details_activity)

            // get data for movie title, release date, overview, and poster URL
            val title = intent.getStringExtra(MainActivity.KEY_MOVIE_TITLE)
            val releaseDate = intent.getStringExtra(MainActivity.KEY_MOVIE_RELEASE_DATE)
            val overview = intent.getStringExtra(MainActivity.KEY_MOVIE_OVERVIEW)
            val posterUrl = intent.getStringExtra(MainActivity.KEY_MOVIE_POSTER_URL)

            // set data in views
            val titleView = findViewById<TextView>(R.id.tvMovieTitle)
            val releaseDateView = findViewById<TextView>(R.id.tvReleaseDate)
            val overviewView = findViewById<TextView>(R.id.tvMovieOverview)
            val posterView = findViewById<ImageView>(R.id.ivMoviePoster)

            titleView.text = title
            releaseDateView.text = releaseDate
            overviewView.text = overview
            Glide.with(this).load(posterUrl).into(posterView)
        }
    }

}