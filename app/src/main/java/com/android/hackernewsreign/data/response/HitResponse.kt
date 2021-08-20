package com.android.hackernewsreign.data.response

import com.google.gson.annotations.SerializedName

data class HitResponse (
    @SerializedName("created_at") val created_at: String? = null,
    @SerializedName("story_title") val story_title: String? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("story_url") val story_url: String? = null,
    @SerializedName("story_id") val story_id: Int? = null
        )