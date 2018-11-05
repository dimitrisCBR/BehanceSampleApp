package com.cbr.behancesample.domain.di

import android.content.Context
import com.cbr.behancesample.BehanceSampleApplication
import com.futureworkshops.domain.database.RoomDB
import com.futureworkshops.domain.network.BehanceApiService
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by dimitrios on 12/10/2018.
 */
@Singleton
@Component(modules = [
    ApplicationModule::class,
    NetworkModule::class
])
interface ApplicationComponent {

    fun context(): Context

    fun inject(behanceSampleApplication: BehanceSampleApplication)

    fun okHttp(): OkHttpClient

    fun retrofit(): Retrofit

    fun behanceApiService(): BehanceApiService

    fun roomDB(): RoomDB

}