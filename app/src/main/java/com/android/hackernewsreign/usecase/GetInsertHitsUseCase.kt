package com.android.hackernewsreign.usecase

import com.android.hackernewsreign.data.HitRepository
import com.android.hackernewsreign.data.model.HitModel
import javax.inject.Inject

class GetInsertHitsUseCase @Inject constructor(
    private val repository: HitRepository){
    suspend operator fun invoke(hitEntity: List<HitModel>) = repository.insertHits(hitEntity)
}