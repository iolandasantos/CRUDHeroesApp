package br.com.iolandasantos.crudheroesapp.api

import br.com.iolandasantos.crudheroesapp.model.ResponseStudio
import br.com.iolandasantos.crudheroesapp.model.ResponseStudioList
import br.com.iolandasantos.crudheroesapp.model.Studio
import retrofit2.Call
import retrofit2.http.*

interface StudioAPI {

    @GET("/api/studios")
    fun getStudios() : Call<ResponseStudioList>

    @POST("/api/studios")
    fun salvar(@Body studio: Studio): Call<ResponseStudio>

    @GET("/api/studios/{id}")
    fun getStudios(@Path("id") id:String): Call<ResponseStudio>

    @PUT("/api/studios/{id}")
    fun update(@Path("id") id:String, @Body studio: Studio): Call<ResponseStudio>

    @DELETE("/api/studios/{id}")
    fun delete(@Path("id") id:String): Call<ResponseStudio>
}