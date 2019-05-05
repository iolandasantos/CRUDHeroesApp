package br.com.iolandasantos.crudheroesapp.view.form

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.iolandasantos.crudheroesapp.model.Studio
import br.com.iolandasantos.crudheroesapp.model.ResponseStatus
import br.com.iolandasantos.crudheroesapp.repository.StudioRepository

class FormStudioViewModel : ViewModel() {

    val studioRepository = StudioRepository()
    val responseStatus: MutableLiveData<ResponseStatus> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun salvar(
        name: String,
        headquarter: String,
        website: String
    ) {
        isLoading.value = true
        val studio = Studio(name = name, headquarter = headquarter, website = website)
        studioRepository.salvar(studio,
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

    fun atualizar(
        id: String,
        name: String,
        headquarter: String,
        website: String
    ) {
        isLoading.value = true

        val studio = Studio(name = name, headquarter = headquarter, website = website, _id = id)
        studioRepository.atualizar(id, studio,
            onComplete = {
                isLoading.value = false
                responseStatus.value = ResponseStatus(
                    true,
                    "Dado salvo com sucesso"
                )
            }, onError = {
                isLoading.value = false
                responseStatus.value = ResponseStatus(
                    false,
                    it?.message!!
                )
            })

    }

    fun apagar(id: String){
        isLoading.value = true

        studioRepository.apagar(id,
            onComplete = {
                isLoading.value = false
                responseStatus.value = ResponseStatus(
                    true,
                    "Dado removido com sucesso"
                )
            }, onError = {
                isLoading.value = false
                responseStatus.value = ResponseStatus(
                    false,
                    it?.message!!
                )
            })

    }

}