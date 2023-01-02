package com.example.amphibiansapp.model

import com.squareup.moshi.Json



data class ResponseItem(
	val name: String,
	val description: String,
	val type: String,
	@Json(name ="img_src") val imgSrc: String
)

