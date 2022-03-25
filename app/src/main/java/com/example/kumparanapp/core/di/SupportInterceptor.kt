package com.example.kumparanapp.core.di

import okhttp3.Interceptor
import okhttp3.Response

class SupportInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("Content-Type", "application/json")
            .removeHeader("Pragma")
            .header("Cache-Control", String.format("max-age=%d", 432000))
            .build()
        return chain.proceed(request)
    }
}