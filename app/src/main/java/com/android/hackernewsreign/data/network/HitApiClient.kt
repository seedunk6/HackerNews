package com.android.hackernewsreign.data.network

import com.android.hackernewsreign.data.response.HitListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HitApiClient {
    @GET("search_by_date?query=mobile")
    suspend fun getAllHits(
        @Query("page") page: Int
    ): Response <HitListResponse?>
}