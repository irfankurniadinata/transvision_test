package com.example.test.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder

import com.google.gson.Gson
import okhttp3.logging.HttpLoggingInterceptor

val networkModule = module {
    single { provideHttpClient() }
    single { provideRetrofit(get(), "https://requestly.dev/api/mockv2/") }
}

var gson = GsonBuilder()
    .setLenient()
    .create()

const val connectTimeout: Long = 30
const val readTimeout: Long = 30

fun provideHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
        .connectTimeout(connectTimeout, TimeUnit.SECONDS)
        .readTimeout(readTimeout, TimeUnit.SECONDS)
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
        level = HttpLoggingInterceptor.Level.BODY
    }
    okHttpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor)
    okHttpClientBuilder.build()
    return okHttpClientBuilder.build()
}

fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
}