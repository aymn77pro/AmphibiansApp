package com.example.amphibiansapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibiansapp.ui.screen.AmphibiansViewModel
import com.example.amphibiansapp.R
import com.example.amphibiansapp.ui.screen.AmphibiansScreen


@Composable
fun AmphibiansInformtionApp(viewModels: AmphibiansViewModel,modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(stringResource(R.string.app_name))
            })
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            val amphibiansViewModel : AmphibiansViewModel = viewModel()
            AmphibiansScreen(
                amphibiansUiState = amphibiansViewModel.amphibiansUiState,
                retryAction =  amphibiansViewModel::getAmphibiansInformtion
            )
        }
    }
}