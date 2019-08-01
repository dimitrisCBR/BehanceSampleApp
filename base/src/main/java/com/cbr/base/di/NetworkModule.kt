package com.cbr.base.di

import com.cbr.base.BuildConfig
import com.cbr.base.data.network.BehanceApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    fun httpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return clientBuilder.build()
    }


    @Singleton
    @Provides
    fun gson(): Gson {
        //TODO update date-format when finalized from API
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss zzz")
                .create()
    }

    @Singleton
    @Provides
    fun retrofitClient(httpClient: OkHttpClient, gson: Gson): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()


    @Singleton
    @Provides
    fun apiService(retrofit: Retrofit): BehanceApiService {
        return retrofit.create(BehanceApiService::class.java)
    }

}
