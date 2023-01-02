package com.example.amphibiansapp.data

import com.example.amphibiansapp.model.ResponseItem
import retrofit2.http.GET

//App container interface
interface AppContainer {
    val amphibiansInfoRepository : AmphibiansInfoRepository
}

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<ResponseItem>
}