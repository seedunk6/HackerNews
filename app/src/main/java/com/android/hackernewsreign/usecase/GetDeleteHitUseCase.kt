package com.android.hackernewsreign.usecase

import com.android.hackernewsreign.data.HitRepository
import javax.inject.Inject

class GetDeleteHitUseCase @Inject constructor(
    private val repository: HitRepository){
    suspend operator fun invoke(storyId: Int) = repository.insertDeleteIdHit(storyId)
}