package com.itaran.data.repository

import com.itaran.data.api.MainApi
import com.itaran.data.api.RequestsProxy
import com.itaran.domain.models.CustomResult
import com.itaran.domain.repository.MainItemRepository
import com.itaran.data.prefs.IPrefs
import com.itaran.domain.models.home.HomeProductModel

class MainItemRepositoryImpl(private val api: MainApi, private val proxy: RequestsProxy, private val prefs: IPrefs) :
    MainItemRepository {

//    override suspend fun getMainItems(): CustomResult<List<HomeProductModel>> {
//        return  api.getMyItems()
//    }
}