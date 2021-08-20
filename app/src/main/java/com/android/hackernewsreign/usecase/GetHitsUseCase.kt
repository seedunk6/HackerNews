package com.android.hackernewsreign.usecase

import com.android.hackernewsreign.data.HitRepository
import javax.inject.Inject

class GetHitsUseCase @Inject constructor(
    private val repository: HitRepository){
    suspend operator fun invoke(page: Int) = repository.getAllHits(page)
}