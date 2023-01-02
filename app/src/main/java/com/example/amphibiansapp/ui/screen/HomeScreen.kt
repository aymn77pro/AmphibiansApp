package com.example.amphibiansapp.ui.screen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.foundation.layout.R
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.*
import com.example.amphibiansapp.R
import com.example.amphibiansapp.model.ResponseItem
import androidx.compose.material.ButtonColors as ButtonColors1


@Composable
fun AmphibiansScreen(
    amphibiansUiState: AmphibiansUiState,
    modifier: Modifier =Modifier,
    retryAction: () -> Unit
){

    when(amphibiansUiState){
    is AmphibiansUiState.Loading -> LoadingScreen(modifier =  modifier,
        url = "https://assets4.lottiefiles.com/packages/lf20_rwq6ciql.json")
    is AmphibiansUiState.Success -> AmphibiansList(informtion = amphibiansUiState.photos,modifier)
    is AmphibiansUiState.Error -> ErrorScreen(
        modifier =  modifier, retryAction = retryAction)
}

}
@Composable
fun LoadingScreen(modifier: Modifier = Modifier , url: String ) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
     Lottie(url = url)
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,retryAction: () -> Unit
   ) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
     Button(onClick = retryAction, colors = ButtonDefaults.buttonColors(Color.White)) {
         
         LottieErorr()
     }


    }
}

@Composable
fun Lottie(url: String){
    val composition by rememberLottieComposition(LottieCompositionSpec.Url(url))
   // val composition1 by rememberLottieComposition(LottieCompositionSpec.Url("https://assets1.lottiefiles.com/private_files/lf30_wvfo5uzg.json"))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}

@Composable
fun LottieErorr(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.erorr_masg))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}

    @Composable
    fun AmphibianCard(amphibian: ResponseItem, modifier: Modifier = Modifier) {
        Card(modifier = modifier.fillMaxWidth(),
            elevation = 8.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.app_name, amphibian.name.toString(), amphibian.type.toString()),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = amphibian.description.toString(),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                )
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(amphibian.imgSrc)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                   error = painterResource(id = R.drawable.ic_launcher_foreground),
                    placeholder = painterResource(id = R.drawable.ic_launcher_background)
                )
            }
        }
    }

@Composable
fun AmphibiansList(informtion:List<ResponseItem>,modifier: Modifier = Modifier){
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ){
    items(items = informtion, key = { infos -> infos.name.toString() }){
        info -> AmphibianCard(amphibian = info)
    }
    }
}


@Preview(showSystemUi = true, showBackground = false)
@Composable
fun Screen(){


}
