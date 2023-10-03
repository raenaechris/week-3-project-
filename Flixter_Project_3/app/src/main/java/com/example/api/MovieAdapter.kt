package com.example.api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val context: Context, private val movieList: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView = itemView.findViewById<TextView>(R.id.mediaTitle)
        private val overviewTextView = itemView.findViewById<TextView>(R.id.tvMovieOverview)
        private val posterImageView = itemView.findViewById<ImageView>(R.id.ivMoviePoster)

        fun bind(movie: Movie) {
            titleTextView.text = movie.title
            overviewTextView.text = movie.overview
            Glide.with(context).load(movie.posterImageUrl).into(posterImageView)
        }
    }
}

