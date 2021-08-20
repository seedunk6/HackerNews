package com.android.hackernewsreign.data

import com.android.hackernewsreign.data.database.DeleteHitEntity
import com.android.hackernewsreign.data.database.HitDao
import com.android.hackernewsreign.data.model.HitModel
import com.android.hackernewsreign.data.network.HitService
import javax.inject.Inject


class HitRepository @Inject constructor(
    private val api: HitService,
    private val hitDao: HitDao,
    private val mapper: HitMapper){

    suspend fun getAllHits(page: Int): List<HitModel> {
        val response = api.getHits(page)
        val listDeleteIds = hitDao.getDeleteHits().map {it.story_id}
        return mapper.getListHitsModelByHitsResponse(response,listDeleteIds)
    }

    suspend fun getLocalHits(): List<HitModel> {
        val response = hitDao.getAllHits()
        val listDeleteIds = hitDao.getDeleteHits().map {it.story_id}
        return mapper.getListHitsModelByHitEntity(response, listDeleteIds)
    }

    suspend fun insertHits(hitEntity: List<HitModel>) {
        hitDao.insertHits(mapper.getListHitsEntityByHitModel(hitEntity))
    }

    suspend fun insertDeleteIdHit(storyId: Int) {
        hitDao.insertDeleteIdHits(DeleteHitEntity(storyId))
    }
}