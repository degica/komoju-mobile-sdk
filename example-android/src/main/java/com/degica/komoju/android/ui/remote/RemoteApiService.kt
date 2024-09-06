package com.degica.komoju.android.ui.remote

import com.degica.komoju.android.BuildConfig
import com.degica.komoju.android.ui.remote.dtos.CreateSessionRequest
import com.degica.komoju.android.ui.remote.dtos.CreateSessionResponse
import com.degica.komoju.android.ui.remote.dtos.PublishableKeyResponse
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RemoteApiService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("serve-keys")
    suspend fun getPublishableKey(): Response<PublishableKeyResponse>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("create-session")
    suspend fun createSession(@Body request: CreateSessionRequest): Response<CreateSessionResponse>

    companion object {
        fun create(): RemoteApiService = Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .build(),
            )
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.TEST_SERVER_URL)
            .build()
            .create(RemoteApiService::class.java)
    }
}
