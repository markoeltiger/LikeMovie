package com.mark.likemovies.ui.filter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mark.likemovies.Client.SearchApi
import com.mark.likemovies.Helper.RetrofitApiHelper
import com.mark.likemovies.MainActivity
import com.mark.likemovies.R
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

            intent = Intent(applicationContext, MainActivity::class.java)

            startActivity(intent)
            GlobalScope.launch {
                val searchapi = RetrofitApiHelper.getInstance().create(SearchApi::class.java)

                val result =
                    searchapi.getSuggestions(user_id = "35", "1", "drama", "usa", "2000", "2001")
                if (result != null) {


                }


            }

            FancyToast.makeText(
                this,
                "تم تطبيق التخصيصات",
                FancyToast.LENGTH_LONG,
                FancyToast.SUCCESS,
                false
            ).show();
        }


    }
}