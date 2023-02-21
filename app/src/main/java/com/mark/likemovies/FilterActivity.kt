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
import com.skydoves.powerspinner.PowerSpinnerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        var toolbar: Toolbar? = null
        toolbar = findViewById<View>(R.id.toolbar2) as Toolbar
        setSupportActionBar(toolbar)
        val typeSpinner = findViewById<PowerSpinnerView>(R.id.spinnertype)
        val countrySpinner = findViewById<PowerSpinnerView>(R.id.countrySpinner)
        val yearSpinner = findViewById<PowerSpinnerView>(R.id.yearSpinner)

        val applybutton = findViewById<Button>(R.id.applybutton)
        applybutton.setOnClickListener {
            // launching a new coroutine
            intent = Intent(applicationContext, MainActivity::class.java)
//            intent.putExtra("type","suggestions")

//            intent.putExtra("user_id","35")
//            intent.putExtra("genre",typeSpinner.selectedIndex)
//            intent.putExtra("country",countrySpinner.selectedIndex)
//            intent.putExtra("from",yearSpinner.selectedIndex)

            startActivity(intent)
            GlobalScope.launch {
                val searchapi = RetrofitApiHelper.getInstance().create(SearchApi::class.java)

                val result = searchapi.getSuggestions(user_id = "35","1","drama","usa","2000","2001")
                if (result!=null){


                }
                // Checking the results


            }

            FancyToast.makeText(this, "تم تطبيق التخصيصات", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
        }



    }
}