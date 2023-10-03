package com.example.api

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException

const val TAG = "MainActivity"
const val NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MainActivity : AppCompatActivity() {

    private val movieList = mutableListOf<Movie>()
    private lateinit var movieRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        movieRecyclerView = findViewById(R.id.recyclerViewMovies)
        movieRecyclerView.layoutManager = LinearLayoutManager(this)
        movieRecyclerView.adapter = MovieAdapter(this, movieList)

        val client = AsyncHttpClient()
        val params = RequestParams()

        client[ NOW_PLAYING_URL, params, object: JsonHttpResponseHandler() {
            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
               Log.e(TAG, "Failed to fetch movie data with status code: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(TAG, "Successfully fetched movie data with status code: $statusCode")
                try {
                    val movieJsonArray = json.jsonObject.getJSONArray("results")
                    movieList.addAll(Movie.fromJsonArray(movieJsonArray))
                    movieRecyclerView.adapter?.notifyDataSetChanged()
                } catch (e: JSONException) {
                    Log.e(TAG, "Encountered exception while parsing movie data: $e")
                }
            }
        }]
    }
}

