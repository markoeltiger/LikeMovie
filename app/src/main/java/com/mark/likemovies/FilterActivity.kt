package com.mark.likemovies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mark.likemovies.Client.SearchApi
import com.mark.likemovies.Helper.RetrofitApiHelper
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        var toolbar: Toolbar? = null
        toolbar = findViewById<View>(R.id.toolbar2) as Toolbar
        setSupportActionBar(toolbar)
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