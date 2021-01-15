package com.example.breakingbadapp.framework.retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object AnalyticsModule {

  @Provides
  fun provideAnalyticsService(
    // Potential dependencies of this type
  ): WebService {
      return Retrofit.Builder()
               .baseUrl("https://www.breakingbadapi.com/api/")
               .addConverterFactory(GsonConverterFactory.create())
               .build()
               .create(WebService::class.java)
  }
}