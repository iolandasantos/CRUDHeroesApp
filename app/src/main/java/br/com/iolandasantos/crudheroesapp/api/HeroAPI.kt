package br.com.iolandasantos.crudheroesapp.api

import br.com.iolandasantos.crudheroesapp.model.Hero
import br.com.iolandasantos.crudheroesapp.model.ResponseHero
import br.com.iolandasantos.crudheroesapp.model.ResponseHeroList
import retrofit2.Call
import retrofit2.http.*

interface HeroAPI {

    @GET("/api/heroes")
    fun getHeroes() : Call<ResponseHeroList>

    @POST("/api/heroes")
    fun salvar(@Body hero: Hero): Call<Hero>

    @GET("/api/heroes/{id}")
    fun getHeroes(@Path("id") id:String): Call<Hero>

    @PUT("/api/heroes/{id}")
    fun update(@Path("id") id:String, @Body hero: Hero): Call<ResponseHero>

    @DELETE("/api/heroes/{id}")
    fun delete(@Path("id") id:String): Call<ResponseHero>

}