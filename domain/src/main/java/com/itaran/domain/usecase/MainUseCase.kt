package com.itaran.domain.usecase

import com.itaran.domain.models.CustomResult
import com.itaran.domain.models.home.HomeProductModel
import com.itaran.domain.repository.MainItemRepository

class MainUseCase(private val mainItemRepository: MainItemRepository) {

//    suspend fun getMainItems(): CustomResult<List<HomeProductModel>> {
//        return mainItemRepository.getMainItems()
//    }
}