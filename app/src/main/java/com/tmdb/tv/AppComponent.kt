package com.tmdb.tv

import com.google.gson.GsonBuilder
import com.tmdb.tv.data.datasource.local.MovieLocalDataSource
import com.tmdb.tv.data.datasource.local.MovieLocalDataSourceImpl
import com.tmdb.tv.data.database.AppDataBase
import com.tmdb.tv.data.datasource.remote.MovieRemoteDataSource
import com.tmdb.tv.data.datasource.remote.MovieRemoteDataSourceImpl
import com.tmdb.tv.data.repository.MovieRepository
import com.tmdb.tv.data.repository.MovieRepositoryImpl
import com.tmdb.tv.data.service.MovieInterceptor
import com.tmdb.tv.data.service.MovieService
import com.tmdb.tv.domain.usecases.HomeUseCase
import com.tmdb.tv.domain.usecases.LandingUseCase
import com.tmdb.tv.domain.usecases.SplashUseCase
import com.tmdb.tv.domain.utils.Connectivity
import com.tmdb.tv.presentation.features.home.HomeViewModel
import com.tmdb.tv.presentation.features.landing.LandingViewModel
import com.tmdb.tv.presentation.features.splash.SplashViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appComponent = listOf(
    createService("https://api.themoviedb.org/3/"),
    createDataBase(),
    createRepositories(),
    createDataSources(),
    createUseCases(),
    createViewModels(),
    createUtils()
)

fun createViewModels() = module{
    viewModel { HomeViewModel(homeUseCase = get()) }
    viewModel { SplashViewModel(splashUseCase = get()) }
    viewModel { LandingViewModel(landingUseCase = get()) }
}

fun createUseCases() = module{
    factory { SplashUseCase(get()) }
    factory { HomeUseCase(get()) }
    factory { LandingUseCase(get()) }
}

fun createRepositories() = module{
    factory { MovieRepositoryImpl(get(), get(), get()) as MovieRepository }
}

fun createDataSources() = module{
    factory { MovieRemoteDataSourceImpl(get()) as MovieRemoteDataSource }
    factory { MovieLocalDataSourceImpl(get()) as MovieLocalDataSource }
}

fun createDataBase() = module{
    val database = "DATABASE"
    single(named(database)) { AppDataBase.buildDatabase(androidContext()) }
    factory { (get(named(database)) as AppDataBase).movieDao() }
}

fun createUtils() = module{
    factory { Connectivity(get()) }
}

fun createService(baseUrl: String) = module {

    single {
        val logginInterceptor = HttpLoggingInterceptor()
        logginInterceptor.apply { logginInterceptor.level = HttpLoggingInterceptor.Level.BODY }

        val movieInterceptor = MovieInterceptor()

        OkHttpClient.Builder()
            .addInterceptor(logginInterceptor)
            .addInterceptor(movieInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    factory { get<Retrofit>().create(MovieService::class.java) }
}