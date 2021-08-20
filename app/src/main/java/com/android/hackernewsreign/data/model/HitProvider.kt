package com.android.hackernewsreign.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HitProvider @Inject constructor() {
    var hits: HitModel? = null
}