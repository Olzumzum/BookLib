package com.olzumzum.booklib.di.module

import android.app.Application
import com.olzumzum.booklib.server.BookApi
import com.olzumzum.booklib.server.BookServerCommunicator
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    @Provides
    fun provideServerCommutator(bookApi: BookApi, application: Application): BookServerCommunicator {
        return BookServerCommunicator(bookApi, application)
    }

    @Provides
    fun provideIntercaptor(): HttpLoggingInterceptor {
        val networkLogInterceptor = HttpLoggingInterceptor()
        networkLogInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return networkLogInterceptor
    }

    @Provides
    fun provideOkClient(networkLogInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkLogInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofitBuilder(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideBookApi(retrofit: Retrofit): BookApi {
        return retrofit.create(BookApi::class.java)
    }

    companion object {
        private const val API_URL = "https://api.nytimes.com/svc/books/v3/"
    }
}