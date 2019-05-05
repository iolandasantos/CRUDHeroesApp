package br.com.iolandasantos.crudheroesapp.view.form

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.Toast
import br.com.iolandasantos.crudheroesapp.R
import br.com.iolandasantos.crudheroesapp.model.*
import br.com.iolandasantos.crudheroesapp.repository.StudioRepository
import kotlinx.android.synthetic.main.hero_form.*
import kotlinx.android.synthetic.main.loading.*
import kotlinx.android.synthetic.main.studio_form.btSalvar
import kotlinx.android.synthetic.main.studio_form.inputName
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener

class FormHero : AppCompatActivity() {

    var studioSelecionado: Studio? = null

    private lateinit var formHeroViewModel: FormHeroViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hero_form)

        populaSpinner()

        formHeroViewModel = ViewModelProviders.of(this)
            .get(FormHeroViewModel::class.java)

        btSalvar.setOnClickListener {
            formHeroViewModel.salvar(
                inputName.editText?.text.toString(),
                studioSelecionado?._id!!,
                inputPower.editText?.text.toString(),
                inputWeakness.editText?.text.toString()
            )
        }

        registerObserver()
    }
    private fun populaSpinner(){

        val studioRepository = StudioRepository()

        studioRepository.buscarTodos(
            onComplete = {
                val studios: List<Studio>? = it

                val adapter = SpinnerAdapter(this@FormHero, studios!!)

                spStudio.adapter=adapter

                spStudio.onItemSelectedListener = object : OnItemSelectedListener {
                    override fun onItemSelected(
                        parentView: AdapterView<*>,
                        selectedItemView: View,
                        position: Int,
                        id: Long
                    ) {
                        studioSelecionado = adapter.getItem(position) as Studio
                    }

                    override fun onNothingSelected(parentView: AdapterView<*>) {
                        // your code here
                    }

                }
            },
            onError = {

            }
        )
    }


    private fun registerObserver() {
        formHeroViewModel.responseStatus.observe(this, responseObserver)
        formHeroViewModel.isLoading.observe(this, loadingObserver)
    }

    private var loadingObserver = Observer<Boolean> {
        if (it == true) {
            containerLoading.visibility = View.VISIBLE
        } else {
            containerLoading.visibility = View.GONE
        }
    }

    private var responseObserver = Observer<ResponseStatus> {
        Toast.makeText(this, it?.mensagem, Toast.LENGTH_SHORT).show()
        if (it?.sucesso == true) {
            setResult(Activity.RESULT_OK)
            finish()
        }

    }


}
