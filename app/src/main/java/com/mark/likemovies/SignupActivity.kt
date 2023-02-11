package com.mark.likemovies

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mark.likemovies.Client.ApiClient
import com.mark.likemovies.Helper.RetrofitApiHelper
import com.mark.likemovies.Models.user
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var username: EditText? = null
    private var phone: EditText? = null
    private val sharedPrefFile = "sharedpreference"

    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var btnSignUp: Button? = null
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
       btnSignUp!!.setOnClickListener {  val email = inputEmail!!.text.toString().trim()
           val password = inputPassword!!.text.toString().trim()

           if (TextUtils.isEmpty(email)){
               Toast.makeText(applicationContext,"Enter your email Address!!", Toast.LENGTH_LONG).show()

           }
           if (TextUtils.isEmpty(password)){
               Toast.makeText(applicationContext,"Enter your Password",Toast.LENGTH_LONG).show()

           }
           if (password.length < 6){
               Toast.makeText(applicationContext,"Password too short, enter mimimum 6 charcters" , Toast.LENGTH_LONG).show()

           }
           createAccount(username,email, password)  }


    }
    private fun createAccount(username:String,email: String, password: String) {
        // [START create_user_with_email]
//        val retrofit = Retrofit.Builder()
//            .baseUrl(Constants.APIBASEURL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val register = RetrofitApiHelper.getInstance().create(ApiClient::class.java)
//        // launching a new coroutine
//        GlobalScope.launch {
//            val result = register.registerUser(username,email,password,password)
//            result.enqueue(new Callback<com.mark.likemovies.Models.APImodel>() {
//
//
//
//            }
//            // Checking the results
//
//        }







        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    val uid= user?.uid
                    updateUI(user)
                    val email = inputEmail!!.text.toString().trim()
                    val password = inputPassword!!.text.toString().trim()
                    val phone = phone!!.text.toString().trim()
                    val username = username!!.text.toString().trim()

                    val users = user(uid,phone,username, email )
                    if (uid != null) {
                        database.child("users").child(uid).setValue(users)
                    }

                    signIn(email, password)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
        // [END create_user_with_email]
    }}
    private fun setStatusBarWhite(activity: AppCompatActivity){
        //Make status bar icons color dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            activity.window.statusBarColor = Color.WHITE
        }
    }
    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
                        Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor =  sharedPreferences.edit()
                    editor.putBoolean("logged",true)

                    editor.apply()
                    editor.commit()

                    startActivity(Intent(this, MainActivity::class.java))

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
        // [END sign_in_with_email]
    }

    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                // Email Verification sent
            }
        // [END send_email_verification]
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    private fun reload() {

    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}
