package com.example.amphibiansapp.data

import com.example.amphibiansapp.model.ResponseItem

interface AmphibiansInfoRepository{
    suspend fun getAmpibinsInformtion(): List<ResponseItem>
}


// ركز على return type هنا يطلب ترجع الانترفيس حق الريبو

class DefaultAmphibiansInfoRepository(
    private val amphibiansApiService: AmphibiansApiService
    ) :AmphibiansInfoRepository {

    override suspend fun getAmpibinsInformtion(): List<ResponseItem> = amphibiansApiService.getAmphibians()


}

