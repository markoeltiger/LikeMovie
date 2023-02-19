package com.mark.likemovies.di

import com.mark.likemovies.util.Constants
import com.mark.likemovies.data.remote.RemoteApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideApi( builder: Retrofit.Builder):RemoteApiService{
        return builder
            .build()
            .create(RemoteApiService::class.java)

    }
    @Provides
    @Singleton
     fun provideRetrofit():Retrofit.Builder{
        return  Retrofit.Builder()
            .baseUrl(Constants.APIBASEURL)
            .addConverterFactory(GsonConverterFactory.create())

    }
}