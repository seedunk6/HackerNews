package com.android.hackernewsreign.data.response

import com.google.gson.annotations.SerializedName

data class HitListResponse (
    @SerializedName("hits") val listHitsResponse: ArrayList<HitResponse>
    )