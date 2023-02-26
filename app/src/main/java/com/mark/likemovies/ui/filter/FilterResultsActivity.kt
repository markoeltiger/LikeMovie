package com.mark.likemovies.ui.filter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mark.likemovies.R
import com.mark.likemovies.data.models.homeMovies.SingleMovie
import com.mark.likemovies.ui.details.DetailsActivity
import com.mark.likemovies.util.Constants
import com.shashank.sony.fancytoastlib.FancyToast
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
@AndroidEntryPoint

class FilterResultsActivity : AppCompatActivity() {
    lateinit var country: String
    lateinit var year: String
    lateinit var genre: String
    lateinit var userDetails: SharedPreferences
    lateinit var MoviePoster: ImageView
    lateinit var LikeMovie: Button
    lateinit var DisLikeMovie: Button
    lateinit var  Details: TextView
    lateinit var currentMovie : SingleMovie
    var user_id: Int =1
    var CurrentMoviePage: Int =0

    val viewModel: FilterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_results)
          userDetails = this.getSharedPreferences(  Constants.USER_DETAILS_SharedPrefs, Context.MODE_PRIVATE)

        getIntentData();
        getCurrentMovie();
        intiateViews();
    }

    private fun intiateViews() {
        MoviePoster = findViewById<ImageView>(R.id.mymoviposter)
        LikeMovie = findViewById<Button>(R.id.likeMovieImage)
        DisLikeMovie = findViewById<Button>(R.id.unikeMovieImage)
        Details = findViewById<Button>(R.id.moreDetails)
        Details.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("data", currentMovie)
            startActivity(intent)
        }

    }

    private fun getCurrentMovie() {
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                var likeReactResponse =
                    viewModel.getSuggestion(user_id = user_id, CurrentMoviePage, genre, country, year, "2022")
                if (likeReactResponse.isSuccessful) {
                    currentMovie= likeReactResponse.body()!!
                    Picasso.get().load(likeReactResponse.body()?.posters?.get(0)?.image).placeholder(R.drawable.app_logo_2)
                        .into(MoviePoster)

                 }
            } catch (e: Exception) {
                Log.e("cyrrent exception",e.toString())
                FancyToast.makeText(
                    this@FilterResultsActivity,
                    "حدث خطأ أثناء الإعجاب بالفيلم برجاء المحاولة مرة اخري",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    false
                ).show()

            }

        }

          }

    private fun getIntentData() {

        user_id=   userDetails.getString("id", "0")?.toInt()!!
        val intent: Intent = intent
          country     = intent.getStringExtra("country").toString()
          year          = intent.getStringExtra("year").toString()
          genre        = intent.getStringExtra("genre").toString()

    }
}