package com.mark.likemovies.ui.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.mark.likemovies.R
import com.mark.likemovies.ui.home.HomeActivity
import com.mark.likemovies.util.Constants
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
                            editor.putBoolean("logged", true)

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
