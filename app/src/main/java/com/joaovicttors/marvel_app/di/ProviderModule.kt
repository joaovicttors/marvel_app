package com.joaovicttors.marvel_app.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.joaovicttors.data.datasources.remote.service.CharacterRemoteService
import com.joaovicttors.marvel_app.database.MarvelDatabase
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

private const val BASE_URL = "https://gateway.marvel.com/v1/public/"
private const val PUBLIC_KEY = "161efc45f87a82340ae16aa8a72fabab"
private const val PRIVATE_KEY = "b5711dcc9495810a0d76d660acb59d462f8b27ec"

fun String.md5(): String {
    try {
        val digest = MessageDigest.getInstance("MD5")
        digest.update(toByteArray())
        val messageDigest = digest.digest()
        val hexString = StringBuilder()
        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2)
                h = "0$h"
            hexString.append(h)
        }
        return hexString.toString()

    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}

object ProviderModule {

    val module = module {

        single {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor { chain ->
                val originalRequest = chain.request()
                val originalHttpUrl: HttpUrl = originalRequest.url

                val timeStamp =
                    (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", PUBLIC_KEY)
                    .addQueryParameter("hash", "$timeStamp${PRIVATE_KEY}${PUBLIC_KEY}".md5())
                    .addQueryParameter("ts", timeStamp)
                    .build()

                val requestBuilder: Request.Builder = originalRequest.newBuilder()
                    .url(url)

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            return@single builder.build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

        single {
            Room.databaseBuilder(
                androidApplication(),
                MarvelDatabase::class.java,
                "marvel_db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        single { get<MarvelDatabase>().characterDao() }
        single { get<Retrofit>().create(CharacterRemoteService::class.java) }
    }
}