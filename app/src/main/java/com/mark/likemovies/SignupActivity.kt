package com.mark.likemovies

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mark.likemovies.ui.auth.AuthViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var username: EditText? = null
    private var phone: EditText? = null
    private val sharedPrefFile = "sharedpreference"

    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var btnSignUp: Button? = null
    val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { super.onBackPressed() }
        setStatusBarWhite(this@SignupActivity)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        btnSignUp = findViewById(R.id.button_signin) as Button
        phone = findViewById(R.id.et_phone) as EditText
        username = findViewById(R.id.et_username) as EditText
        inputEmail = findViewById(R.id.et_email) as EditText
        inputPassword = findViewById(R.id.et_password) as EditText
        btnSignUp!!.setOnClickListener {
            val email = inputEmail!!.text.toString().trim()
            val password = inputPassword!!.text.toString().trim()
                val username = username!!.text.toString().trim().toString()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "Enter your email Address!!", Toast.LENGTH_LONG)
                    .show()

            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Enter your Password", Toast.LENGTH_LONG).show()

            }
            if (password.length < 6) {
                Toast.makeText(
                    applicationContext,
                    "Password too short, enter mimimum 6 charcters",
                    Toast.LENGTH_LONG
                ).show()

            }
            lifecycleScope.launch(Dispatchers.Main){
                try {
                 var response =  viewModel.registerUser(username,email,password,password)
            if (response.isSuccessful){
                FancyToast.makeText(
                    this@SignupActivity,
                    "${response.body()?.message}",
                    FancyToast.LENGTH_LONG,
                    FancyToast.SUCCESS,
                    false
                ).show()
            }
                }catch (e:Exception){
                    FancyToast.makeText(
                        this@SignupActivity,
                        "هناك خطأ في إنشاء الحساب برجاء مراجعة البيانات",
                        FancyToast.LENGTH_LONG,
                        FancyToast.ERROR,
                        false
                    ).show()

                }

            }
         }


    }


    }


private fun setStatusBarWhite(activity: AppCompatActivity) {
    //Make status bar icons color dark
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity.window.statusBarColor = Color.WHITE
    }
}



