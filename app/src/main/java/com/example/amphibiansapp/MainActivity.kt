package com.example.amphibiansapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibiansapp.ui.AmphibiansInformtionApp
import com.example.amphibiansapp.ui.screen.AmphibiansViewModel
import com.example.amphibiansapp.ui.theme.AmphibiansAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          AmphibiansAppTheme {
              val viewModel:AmphibiansViewModel =viewModel(factory = AmphibiansViewModel.Factory)
              AmphibiansInformtionApp(viewModel)
          }
           }
        }
    }



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}