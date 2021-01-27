package com.tmdb.tv

import android.app.Application
import com.tmdb.tv.domain.utils.Connectivity
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    open fun configureDi() =
        startKoin {
            androidContext(this@App)
            modules(provideComponent())
        }

    open fun provideComponent() = appComponent
}