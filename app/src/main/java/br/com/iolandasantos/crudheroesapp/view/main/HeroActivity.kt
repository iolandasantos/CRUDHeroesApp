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
import br.com.iolandasantos.crudheroesapp.model.Hero
import br.com.iolandasantos.crudheroesapp.view.form.FormHero
import kotlinx.android.synthetic.main.activity_hero.*
import kotlinx.android.synthetic.main.content_hero.*
import kotlinx.android.synthetic.main.loading.*

class HeroActivity : AppCompatActivity() {

    lateinit var heroViewModel: HeroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)

        heroViewModel = ViewModelProviders.of(this)
            .get(HeroViewModel::class.java)

        registerObservers()

        heroViewModel.buscarTodos()

        fab.setOnClickListener { view ->
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
            startActivityForResult(
                Intent(
                    this,
                    FormHero::class.java
                ), 1
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            heroViewModel.buscarTodos()
        }
    }

    private fun registerObservers() {
        heroViewModel.isLoading.observe(this, isLoadingObserver)
        heroViewModel.mensagemErro.observe(this, mensagemErroObserver)
        heroViewModel.heroes.observe(this, heroesObserver)
    }

    private var heroesObserver = Observer<List<Hero>> {

        rvHeroes.adapter = HeroListAdapter(
            this,
            it!!
        ) {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            val nextScreenIntent = Intent(this, FormHero::class.java)
            nextScreenIntent.putExtra("ID", it._id)
            nextScreenIntent.putExtra("NAME", it.name)
            nextScreenIntent.putExtra("STUDIO", it.studio)
            nextScreenIntent.putExtra("POWER", it.power)
            nextScreenIntent.putExtra("WEAKNESS", it.weakness)
            startActivityForResult(nextScreenIntent, 1)
        }

        rvHeroes.layoutManager = LinearLayoutManager(this)
        //rvHeroes.layoutManager = GridLayoutManager(this, 3)

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
