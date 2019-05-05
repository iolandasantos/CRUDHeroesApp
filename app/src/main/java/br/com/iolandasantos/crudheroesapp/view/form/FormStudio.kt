package br.com.iolandasantos.crudheroesapp.view.form

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.iolandasantos.crudheroesapp.R
import br.com.iolandasantos.crudheroesapp.model.ResponseStatus
import kotlinx.android.synthetic.main.studio_form.*
import kotlinx.android.synthetic.main.loading.*
import kotlinx.android.synthetic.main.studio_form.btSalvar
import kotlinx.android.synthetic.main.studio_form.inputName

class FormStudio : AppCompatActivity() {

    private lateinit var formStudioViewModel: FormStudioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.studio_form)

        if(intent.extras !=  null) {
            inputID.editText?.setText(intent.getStringExtra("ID"))
            inputName.editText?.setText(intent.getStringExtra("NAME"))
            inputHeadquarter.editText?.setText(intent.getStringExtra("HEADQUARTER"))
            inputWebsite.editText?.setText(intent.getStringExtra("WEBSITE"))

            btRemover.visibility = View.VISIBLE
        }else{
            btRemover.visibility = View.GONE
        }

        formStudioViewModel = ViewModelProviders.of(this)
            .get(FormStudioViewModel::class.java)

        btSalvar.setOnClickListener {
            if(inputID.editText?.text.toString() != ""){
                formStudioViewModel.atualizar(
                    inputID.editText?.text.toString(),
                    inputName.editText?.text.toString(),
                    inputHeadquarter.editText?.text.toString(),
                    inputWebsite.editText?.text.toString()
                )
            }else {
                formStudioViewModel.salvar(
                    inputName.editText?.text.toString(),
                    inputHeadquarter.editText?.text.toString(),
                    inputWebsite.editText?.text.toString()
                )
            }
        }

        btRemover.setOnClickListener{
            formStudioViewModel.apagar(
                inputID.editText?.text.toString()
            )
        }

        registerObserver()
    }

    private fun registerObserver() {
        formStudioViewModel.responseStatus.observe(this, responseObserver)
        formStudioViewModel.isLoading.observe(this, loadingObserver)
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
