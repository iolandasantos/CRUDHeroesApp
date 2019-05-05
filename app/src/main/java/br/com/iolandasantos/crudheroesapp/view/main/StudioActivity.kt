package br.com.iolandasantos.crudheroesapp.view.main

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import br.com.iolandasantos.crudheroesapp.R
import br.com.iolandasantos.crudheroesapp.model.Studio
import br.com.iolandasantos.crudheroesapp.view.form.FormStudio
import kotlinx.android.synthetic.main.activity_studio.*
import kotlinx.android.synthetic.main.content_studio.*
import kotlinx.android.synthetic.main.loading.*

class StudioActivity : AppCompatActivity() {

    lateinit var studioViewModel: StudioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studio)

        studioViewModel = ViewModelProviders.of(this)
            .get(StudioViewModel::class.java)

        registerObservers()

        studioViewModel.buscarTodos()

        fab.setOnClickListener {
            startActivityForResult(
                Intent(
                    this,
                    FormStudio::class.java
                ), 1
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            studioViewModel.buscarTodos()
        }
    }

    private fun registerObservers() {
        studioViewModel.isLoading.observe(this, isLoadingObserver)
        studioViewModel.mensagemErro.observe(this, mensagemErroObserver)
        studioViewModel.studios.observe(this, studiosObserver)
    }

    private var studiosObserver = Observer<List<Studio>> {
        rvStudios.adapter = StudioListAdapter(
            this,
            it!!
        ) {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            val nextScreenIntent = Intent(this, FormStudio::class.java)
            nextScreenIntent.putExtra("ID", it._id)
            nextScreenIntent.putExtra("NAME", it.name)
            nextScreenIntent.putExtra("HEADQUARTER", it.headquarter)
            nextScreenIntent.putExtra("WEBSITE", it.website)
            startActivityForResult(nextScreenIntent, 1)
        }

        rvStudios.layoutManager = LinearLayoutManager(this)
        //rvStudios.layoutManager = GridLayoutManager(this, 3)

    }

    private var mensagemErroObserver = Observer<String> {
        if (it!!.isNotEmpty()) {
            Toast.makeText(
                this,
                it, Toast.LENGTH_LONG
            ).show()
        }
    }

    private var isLoadingObserver = Observer<Boolean> {
        if (it == true) {
            containerLoading.visibility = View.VISIBLE
        } else {
            containerLoading.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar spinner_studio_item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
