package com.example.amphibiansapp.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibiansapp.AmpibiansApplication
import com.example.amphibiansapp.data.AmphibiansInfoRepository
import com.example.amphibiansapp.model.ResponseItem
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibiansUiState {
    data class Success(val photos: List<ResponseItem>) : AmphibiansUiState
    object Error : AmphibiansUiState
    object Loading : AmphibiansUiState
}


class AmphibiansViewModel(
private val amphibiansInfoRepository: AmphibiansInfoRepository
):ViewModel() {
    var amphibiansUiState:AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibiansInformtion()
    }

     fun getAmphibiansInformtion() {

        viewModelScope.launch {
            amphibiansUiState = AmphibiansUiState.Loading
            amphibiansUiState = try {
                 AmphibiansUiState.Success(amphibiansInfoRepository.getAmpibinsInformtion())
            } catch (e:IOException){ AmphibiansUiState.Error
            } catch (e:HttpException){ AmphibiansUiState.Error
            }
    }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AmpibiansApplication)
                val amphibiansInfoRepository = application.container.amphibiansInfoRepository
                AmphibiansViewModel(amphibiansInfoRepository = amphibiansInfoRepository)
            }
        }
    }
}