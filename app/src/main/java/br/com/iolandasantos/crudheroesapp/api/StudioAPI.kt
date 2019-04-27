package br.com.iolandasantos.crudheroesapp.api

import br.com.iolandasantos.crudheroesapp.model.ResponseStudio
import br.com.iolandasantos.crudheroesapp.model.Studio
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StudioAPI {

    @GET("/api/studios")
    fun getStudios() : Call<ResponseStudio>

    @POST("/api/studios")
    fun salvar(@Body studio: Studio): Call<Studio>

    @GET("/api/studios/{id}")
    fun getStudios(@Path("id") id:String): Call<Studio>
}