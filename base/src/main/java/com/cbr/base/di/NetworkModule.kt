package com.cbr.base.di

import com.cbr.base.BuildConfig
import com.cbr.base.data.network.BehanceApiService
import com.google.gson.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*
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
        return GsonBuilder()
                .registerTypeAdapter(Date::class.java, object : JsonDeserializer<Date> {
                    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Date {
                        return Date(json.asJsonPrimitive.asLong * 1000)
                    }

                })
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
