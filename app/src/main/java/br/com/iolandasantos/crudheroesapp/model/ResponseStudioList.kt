package br.com.iolandasantos.crudheroesapp.model

data class ResponseStudioList (
    val status: String,
    val message: String,
    val data: List<Studio>? = null
)