package br.com.iolandasantos.crudheroesapp.view.form

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.iolandasantos.crudheroesapp.model.Hero
import br.com.iolandasantos.crudheroesapp.model.ResponseStatus
import br.com.iolandasantos.crudheroesapp.model.Studio
import br.com.iolandasantos.crudheroesapp.repository.HeroRepository
import br.com.iolandasantos.crudheroesapp.repository.StudioRepository

class FormHeroViewModel : ViewModel() {

    val heroRepository = HeroRepository()
    val responseStatus: MutableLiveData<ResponseStatus> = MutableLiveData()

    val studioRepository = StudioRepository()
    val studios : MutableLiveData<List<Studio>> = MutableLiveData()
    val mensagemErro : MutableLiveData<String> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun salvar(
        name: String,
        studio: String,
        power: String,
        weakness: String
    ) {
        isLoading.value = true
        val hero = Hero(name = name, studio = studio, power = power, weakness = weakness)
        heroRepository.salvar(hero,
            onComplete = {
                isLoading.value = false
                responseStatus.value = ResponseStatus(
                    true,
                    "Dado gravado com sucesso"
                )
            }, onError = {
                isLoading.value = false
                responseStatus.value = ResponseStatus(
                    false,
                    it?.message!!
                )
            })

    }

    fun buscarStudios () {
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