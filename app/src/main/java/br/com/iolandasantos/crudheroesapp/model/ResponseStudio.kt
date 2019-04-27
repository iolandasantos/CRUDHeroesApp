package br.com.iolandasantos.crudheroesapp.model

data class ResponseStudio (
    val status: String,
    val message: String,
    val data: List<Studio>? = null
)