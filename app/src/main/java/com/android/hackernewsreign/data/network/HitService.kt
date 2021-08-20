package com.android.hackernewsreign.data.network

import com.android.hackernewsreign.data.response.HitListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HitService @Inject constructor(
    private val api: HitApiClient) {
    suspend fun getHits(page: Int): HitListResponse? {
        return withContext(Dispatchers.IO) {
            val response = api.getAllHits(page)
            response.body()
        }
    }
}