package com.android.hackernewsreign.usecase

import com.android.hackernewsreign.data.HitRepository
import javax.inject.Inject

class GetLocalHitsUserCase  @Inject constructor(
    private val repository: HitRepository){
    suspend operator fun invoke() = repository.getLocalHits()
}
