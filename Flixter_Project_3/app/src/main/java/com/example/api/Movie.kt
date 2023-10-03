package com.example.api

import org.json.JSONArray

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val overview: String
) {
    val posterImageUrl = "https://image.tmdb.org/t/p/w500/$posterPath"

    companion object {
        fun fromJsonArray(jsonArray: JSONArray): List<Movie> {
            val movieList = mutableListOf<Movie>()
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val movie = Movie(
                    jsonObject.getInt("id"),
                    jsonObject.getString("title"),
                    jsonObject.getString("poster_path"),
                    jsonObject.getString("overview")
                )
                movieList.add(movie)
            }
            return movieList
        }
    }
}

