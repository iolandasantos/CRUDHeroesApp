package br.com.iolandasantos.crudheroesapp.view.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.iolandasantos.crudheroesapp.model.Studio
import br.com.iolandasantos.crudheroesapp.repository.StudioRepository

class StudioViewModel : ViewModel() {

    val studioRepository = StudioRepository()

    val studios : MutableLiveData<List<Studio>> = MutableLiveData()
    val mensagemErro : MutableLiveData<String> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()


    fun buscarTodos () {
        isLoading.value = true
        studioRepository.buscarTodos(
            onComplete = {
                isLoading.value = false
                studios.value = it

            },
            onError = {
                isLoading.value = false
                mensagemErro.value = it?.message
            }
        )
    }

}