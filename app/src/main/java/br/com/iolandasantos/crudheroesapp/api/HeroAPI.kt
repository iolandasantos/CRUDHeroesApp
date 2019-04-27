package br.com.iolandasantos.crudheroesapp.api

import br.com.iolandasantos.crudheroesapp.model.Hero
import br.com.iolandasantos.crudheroesapp.model.ResponseHero
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HeroAPI {

    @GET("/api/heroes")
    fun getHeroes() : Call<ResponseHero>

    @POST("/api/heroes")
    fun salvar(@Body hero: Hero): Call<Hero>

    @GET("/api/heroes/{id}")
    fun getHeroes(@Path("id") id:String): Call<Hero>

}