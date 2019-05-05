package br.com.iolandasantos.crudheroesapp.model

data class ResponseHeroList (
    val status: String,
    val message: String,
    val data: List<Hero>? = null
)