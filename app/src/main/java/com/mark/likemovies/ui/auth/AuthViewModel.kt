package com.mark.likemovies.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mark.likemovies.data.models.register.RegisterResponse
import com.mark.likemovies.repo.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepo: AuthRepo,

): ViewModel(){
    private val _resp= MutableLiveData <Response<RegisterResponse>>()
    val registerResponse: LiveData <Response<RegisterResponse>>
        get()= _resp
    suspend fun registerUser(name:String, email: String, password: String, passwordConfrimation: String) : Response<RegisterResponse>{
    return authRepo.registerNewUser(name,email,password,passwordConfrimation)
    }
    suspend fun signIn(  email: String, password: String ) : Response<RegisterResponse>{
        return authRepo.signIn( email,password )
    }

}