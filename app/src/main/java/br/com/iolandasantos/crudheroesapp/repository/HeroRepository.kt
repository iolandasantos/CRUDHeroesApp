package br.com.iolandasantos.crudheroesapp.repository

import br.com.iolandasantos.crudheroesapp.api.getHeroAPI
import br.com.iolandasantos.crudheroesapp.model.ResponseHero
import br.com.iolandasantos.crudheroesapp.model.Hero
import br.com.iolandasantos.crudheroesapp.model.ResponseHeroList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroRepository {

    fun buscarTodos(
        onComplete:(List<Hero>?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {

        getHeroAPI()
            .getHeroes()
            .enqueue(object : Callback<ResponseHeroList>{
                override fun onFailure(call: Call<ResponseHeroList>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<ResponseHeroList>, response: Response<ResponseHeroList>) {
                    if(response.body()!!.status == "success") {
                        onComplete(response.body()!!.data)
                    } else {
                        onError(Throwable("Erro ao buscar os dados"))
                    }
                }
            })

    }



    fun salvar(hero: Hero,
                onComplete: (Hero) -> Unit,
                onError: (Throwable?) -> Unit) {
        getHeroAPI()
            .salvar(hero)
            .enqueue(object : Callback<Hero>{
                override fun onFailure(call: Call<Hero>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<Hero>, response: Response<Hero>) {
                    onComplete(response.body()!!)
                }
            })
    }

    fun atualizar(id: String,
                  hero: Hero,
                  onComplete: (Hero) -> Unit,
                  onError: (Throwable?) -> Unit) {
        getHeroAPI()
            .update(id, hero)
            .enqueue(object : Callback<ResponseHero>{
                override fun onFailure(call: Call<ResponseHero>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<ResponseHero>, response: Response<ResponseHero>) {
                    if(response.body()!!.status == "success") {
                        onComplete(response.body()?.data!!)
                    } else {
                        onError(Throwable("Erro ao atualizar os dados"))
                    }
                }
            })
    }

    fun apagar(id: String,
               onComplete: () -> Unit,
               onError: (Throwable?) -> Unit) {
        getHeroAPI()
            .delete(id)
            .enqueue(object : Callback<ResponseHero>{
                override fun onFailure(call: Call<ResponseHero>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<ResponseHero>, response: Response<ResponseHero>) {
                    if(response.body()!!.status == "success") {
                        onComplete()
                    } else {
                        onError(Throwable("Erro ao remover os dados"))
                    }
                }
            })
    }





}