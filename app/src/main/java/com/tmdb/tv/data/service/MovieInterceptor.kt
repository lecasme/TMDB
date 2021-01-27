package com.tmdb.tv.data.service

import okhttp3.Interceptor
import okhttp3.Response

class MovieInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().addHeader("Authorization", "Bearer $TOKEN").build()
        return chain.proceed(request)
    }

    companion object{
        const val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ZjdjYTM4MWUyMTIzMWM2ZTU3NTExYTRhOWI3ZjU1NiIsInN1YiI6IjYwMTA5MTQ0ZWE4NGM3MDAzYmI5Y2Y3NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.lSX9BKiQqwGawp6I5FneqWRGhMwUT-2gBYqFqLZ1r-8"
    }

}