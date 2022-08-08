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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private lateinit var auth: FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var btnSignIn: Button? = null
    private val sharedPrefFile = "sharedpreference"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        auth = FirebaseAuth.getInstance()
        inputEmail = findViewById(R.id.et_username) as EditText
        inputPassword = findViewById(R.id.et_password) as EditText
        btnSignIn = findViewById(R.id.button_signin) as Button
btnSignIn!!.setOnClickListener {
    val email = inputEmail!!.text.toString().trim()
    val password = inputPassword!!.text.toString().trim()

    if (TextUtils.isEmpty(email)){
        Toast.makeText(baseContext,"Enter your email Address!!", Toast.LENGTH_LONG).show()

    }
   else if (TextUtils.isEmpty(password)){
        Toast.makeText(baseContext,"Enter your Password", Toast.LENGTH_LONG).show()

    }
   else if (password.length < 6){
        Toast.makeText(applicationContext,"Password too short, enter mimimum 6 charcters" , Toast.LENGTH_LONG).show()

    }
 else   signIn(email, password)
}
    }
    private fun setStatusBarTransparent(activity: AppCompatActivity){
        //Make Status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        //Make status bar icons color dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            activity.window.statusBarColor = Color.WHITE
        }
    }

    fun onClick(view: View) {
        if(view.id == R.id.button_signup){
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
        } else if(view.id == R.id.button_forgot_password){
//            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }
    }
    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
                        Context.MODE_PRIVATE)
                    val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                    editor.putBoolean("logged",true)

                    editor.apply()
                    editor.commit()

                    startActivity(Intent(this, MainActivity::class.java))

                } else {
                    // If sign in fails, display a message to the user.
                     Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
        // [END sign_in_with_email]
    }
    private fun updateUI(user: FirebaseUser?) {

    }

}