package br.iesb.mobile.netflics.di

import br.iesb.mobile.netflics.service.FilmeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class FilmesModule {

    @Provides
    fun providerRetrofit(): Retrofit {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://sujeitoprogramador.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun filmeService(retrofit: Retrofit): FilmeService =
        retrofit.create(FilmeService::class.java)
}