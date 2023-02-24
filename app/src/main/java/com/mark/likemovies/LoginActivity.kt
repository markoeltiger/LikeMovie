package com.mark.likemovies

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mark.likemovies.ui.auth.AuthViewModel
import com.mark.likemovies.ui.home.HomeActivity
import com.mark.likemovies.util.Constants
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var auth: FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var btnSignIn: Button? = null
    val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        inputEmail = findViewById(R.id.et_username) as EditText
        inputPassword = findViewById(R.id.et_password) as EditText
        btnSignIn = findViewById(R.id.button_signin) as Button
        btnSignIn!!.setOnClickListener {
            val email = inputEmail!!.text.toString().trim()
            val password = inputPassword!!.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(baseContext, "Enter your email Address!!", Toast.LENGTH_LONG).show()

            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(baseContext, "Enter your Password", Toast.LENGTH_LONG).show()

            } else if (password.length < 6) {
                Toast.makeText(
                    applicationContext,
                    "Password too short, enter mimimum 6 charcters",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                lifecycleScope.launch(Dispatchers.Main) {
                    try {
                        var response = viewModel.signIn(email, password)
                        if (response.isSuccessful) {
                            FancyToast.makeText(
                                this@LoginActivity,
                                "${response.body()?.message}",
                                FancyToast.LENGTH_LONG,
                                FancyToast.SUCCESS,
                                false
                            ).show()
                            val sharedPreference = getSharedPreferences(
                                Constants.USER_DETAILS_SharedPrefs,
                                Context.MODE_PRIVATE
                            )
                            var editor = sharedPreference.edit()
                            editor.putString("username", "${response.body()?.data?.name}")
                            editor.putString("email", "${response.body()?.data?.email}")
                            editor.putString("id", "${response.body()?.data?.id}")
                            editor.apply()
                            var HomeIntent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(HomeIntent)
                        }
                    } catch (e: Exception) {
                        FancyToast.makeText(
                            this@LoginActivity,
                            "هناك خطأ في تسجيل الدخول برجاء مراجعة البيانات",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,
                            false
                        ).show()

                    }

                }

            }

        }
    }
}




