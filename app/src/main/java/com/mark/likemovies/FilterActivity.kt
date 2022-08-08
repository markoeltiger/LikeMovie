package com.mark.likemovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.Button
import com.mark.likemovies.Client.SearchApi
import com.mark.likemovies.Helper.RetrofitApiHelper
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        val applybutton = findViewById<Button>(R.id.applybutton)
        applybutton.setOnClickListener {
            intent = Intent(applicationContext, predicted::class.java)
            startActivity(intent)
            FancyToast.makeText(this, "تم تطبيق التخصيصات", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
        }


        val quotesApi = RetrofitApiHelper.getInstance().create(SearchApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = quotesApi.getQuotes("adventure,comedy,crime")
            if (result != null)
            // Checking the results
                Log.d("ayush: ", result.message().toString())
            Log.d("ayush: ", result.errorBody().toString())

            Log.d("ayush: ", result.errorBody().toString())

            Log.d("ayush: ", result.body().toString())
        }
    }
}