package br.com.iolandasantos.crudheroesapp.repository

import br.com.iolandasantos.crudheroesapp.api.getStudioAPI
import br.com.iolandasantos.crudheroesapp.model.ResponseStudio
import br.com.iolandasantos.crudheroesapp.model.Studio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudioRepository {

    fun buscarTodos(
        onComplete:(List<Studio>?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {

        getStudioAPI()
            .getStudios()
            .enqueue(object : Callback<ResponseStudio>{
                override fun onFailure(call: Call<ResponseStudio>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<ResponseStudio>, response: Response<ResponseStudio>) {
                    if(response.body()!!.status == "success") {
                        onComplete(response.body()!!.data)
                    } else {
                        onError(Throwable("Erro ao buscar os dados"))
                    }
                }
            })

    }



    fun salvar(studio: Studio,
                onComplete: (Studio) -> Unit,
                onError: (Throwable?) -> Unit) {
        getStudioAPI()
            .salvar(studio)
            .enqueue(object : Callback<Studio>{
                override fun onFailure(call: Call<Studio>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<Studio>, response: Response<Studio>) {
                    onComplete(response.body()!!)
                }
            })
    }





}