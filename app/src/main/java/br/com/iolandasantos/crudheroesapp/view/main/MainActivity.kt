package br.com.iolandasantos.crudheroesapp.view.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.iolandasantos.crudheroesapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivStudio.setOnClickListener {
            val nextScreenIntent = Intent(this,StudioActivity::class.java)
            startActivity(nextScreenIntent)
        }

        ivHero.setOnClickListener {
            startActivity(Intent(this,HeroActivity::class.java))
        }
    }
}
