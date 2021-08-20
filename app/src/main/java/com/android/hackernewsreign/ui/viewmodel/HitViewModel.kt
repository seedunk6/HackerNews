package com.android.hackernewsreign.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hackernewsreign.R
import com.android.hackernewsreign.data.model.HitModel
import com.android.hackernewsreign.usecase.GetDeleteHitUseCase
import com.android.hackernewsreign.usecase.GetHitsUseCase
import com.android.hackernewsreign.usecase.GetInsertHitsUseCase
import com.android.hackernewsreign.usecase.GetLocalHitsUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HitViewModel @Inject constructor(
    private val getHitsUseCase: GetHitsUseCase,
    private val getLocalHitsUserCase: GetLocalHitsUserCase,
    private val getInsertHitsUseCase: GetInsertHitsUseCase,
    private val getDeleteHitUseCase: GetDeleteHitUseCase
    ): ViewModel() {

    val hitModel = MutableLiveData<List<HitModel>>()
    val isLoading = MutableLiveData<Boolean>()
    val showToast = MutableLiveData<Int>()

    private var page = 0
    private var isEndPage = false

    fun getData(nextPage:Boolean = false) {
        if(isLoading.value == true || isEndPage){
            return
        }
        if(nextPage){
            page++
        }
        viewModelScope.launch {
            isLoading.postValue(true)
            var result: List<HitModel>? = null
            try {
               result = getHitsUseCase(page)
            }catch (e: Exception){
                showToast.postValue(R.string.connection_not_available)
            }

            result?.let {
                if(it.isNotEmpty() ){
                    hitModel.postValue(it)
                    saveForLocalHits(it)
                }else{
                    isEndPage = true
                }
            }
            isLoading.postValue(false)
        }
    }

    fun saveForLocalHits(list: List<HitModel>) {
        viewModelScope.launch {
            getInsertHitsUseCase(list)
        }
    }

    fun getLocalList() {
        viewModelScope.launch {
            val result = getLocalHitsUserCase()
            if(result.isNotEmpty() ){
                hitModel.postValue(result)
            }
        }
    }

    fun init() {
        getLocalList()
        getData()
    }

    fun saveHitDelete(storyId: Int) {
        viewModelScope.launch {
            getDeleteHitUseCase(storyId)
        }
    }
}