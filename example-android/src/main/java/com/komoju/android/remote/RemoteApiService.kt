package com.komoju.android.remote

import com.komoju.android.BuildConfig
import com.komoju.android.remote.dtos.CreateSessionRequest
import com.komoju.android.remote.dtos.CreateSessionResponse
import com.komoju.android.remote.dtos.PublishableKeyResponse
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RemoteApiService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("serve-key")
    suspend fun getPublishableKey(): Response<PublishableKeyResponse>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("create-session")
    suspend fun createSession(@Body request: CreateSessionRequest): Response<CreateSessionResponse>

    companion object {
        fun create(): RemoteApiService = Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS) // Set the connection timeout to 100 seconds because the glitch server is slow
                    .readTimeout(100, TimeUnit.SECONDS) // Set the read timeout to 100 seconds because the glitch server is slow
                    .build(),
            )
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .baseUrl(BuildConfig.SERVER_URL)
            .build()
            .create(RemoteApiService::class.java)
    }
}
