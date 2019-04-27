package br.com.iolandasantos.crudheroesapp.view.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.iolandasantos.crudheroesapp.model.Hero
import br.com.iolandasantos.crudheroesapp.repository.HeroRepository

class HeroViewModel : ViewModel() {

    val heroRepository = HeroRepository()

    val heroes : MutableLiveData<List<Hero>> = MutableLiveData()
    val mensagemErro : MutableLiveData<String> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()


    fun buscarTodos () {
        isLoading.value = true
        heroRepository.buscarTodos(
            onComplete = {
                isLoading.value = false
                heroes.value = it

            },
            onError = {
                isLoading.value = false
                mensagemErro.value = it?.message
            }
        )
    }

}