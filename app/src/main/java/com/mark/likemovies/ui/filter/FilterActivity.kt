package com.mark.likemovies.ui.filter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mark.likemovies.R
import com.shashank.sony.fancytoastlib.FancyToast
import com.skydoves.powerspinner.PowerSpinnerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        val Counties = resources.getStringArray(R.array.Counties)
        val Genres = resources.getStringArray(R.array.Genre)
        val Years = resources.getStringArray(R.array.Years)

        applybutton.setOnClickListener {

            intent = Intent(this, FilterResultsActivity::class.java)
            intent.putExtra("country","Untited States")
            intent.putExtra("genre",Genres.get(typeSpinner.selectedIndex))
            intent.putExtra("year",Years.get(yearSpinner.selectedIndex))

            startActivity(intent)


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