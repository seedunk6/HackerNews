package com.android.hackernewsreign.di

import android.content.Context
import androidx.room.Room
import com.android.hackernewsreign.data.HitMapper
import com.android.hackernewsreign.data.database.HitDao
import com.android.hackernewsreign.data.network.HitApiClient
import com.android.hackernewsreign.data.database.HitDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DependenciesModule {

    private const val baseUrl: String = "https://hn.algolia.com/api/v1/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideHitApiClient(retrofit: Retrofit): HitApiClient {
        return retrofit.create(HitApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): HitDatabase {
        return Room
            .databaseBuilder(context, HitDatabase::class.java, "hits")
            .build()
    }

    @Provides
    @Singleton
    fun provideMapper(@ApplicationContext context: Context): HitMapper {
        return HitMapper(context.resources)
    }

    @Provides
    @Singleton
    fun provideNewDao(newDatabase: HitDatabase): HitDao {
        return newDatabase.hitDao
    }

}