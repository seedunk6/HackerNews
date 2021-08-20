package com.android.hackernewsreign.data

import android.content.res.Resources
import com.android.hackernewsreign.R
import com.android.hackernewsreign.data.database.HitEntity
import com.android.hackernewsreign.data.model.HitModel
import com.android.hackernewsreign.data.response.HitListResponse

class HitMapper constructor(private val resources: Resources) {

    fun getListHitsModelByHitsResponse(response: HitListResponse?, listDeleteIds: List<Int>
    ): List<HitModel> {
        val result = arrayListOf<HitModel>()
        if (response != null ) {
            for (item in response.listHitsResponse) {
                if(!listDeleteIds.contains(item.story_id)){
                    item.story_id?.let {
                        result.add(
                            HitModel(
                                id = it,
                                title = item.story_title
                                    ?: resources.getString(R.string.content_not_available),
                                author = item.author
                                    ?: resources.getString(R.string.author_not_available),
                                date = item.created_at
                                    ?: resources.getString(R.string.date_not_available),
                                url = item.story_url ?: ""
                            )
                        )
                    }
                }
            }
        }
        return result
    }

    fun getListHitsEntityByHitModel(list: List<HitModel>): List<HitEntity> {
        val result = arrayListOf<HitEntity>()
        for (item in list) {
            result.add(
                HitEntity(
                    story_id = item.id,
                    story_title = item.title,
                    author = item.author,
                    created_at = item.date,
                    story_url = item.url
                )
            )

        }

        return result
    }

    fun getListHitsModelByHitEntity(list: List<HitEntity>, listDeleteIds: List<Int>
    ): List<HitModel> {
        val result = arrayListOf<HitModel>()

        for (item in list) {
            if (!listDeleteIds.contains(item.story_id)) {
                result.add(
                    HitModel(
                        id = item.story_id,
                        title = item.story_title,
                        author = item.author,
                        date = item.created_at,
                        url = item.story_url
                    )
                )
            }
        }

        return result
    }
}