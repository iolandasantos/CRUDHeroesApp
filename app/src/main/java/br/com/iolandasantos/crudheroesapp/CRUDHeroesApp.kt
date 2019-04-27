package br.com.iolandasantos.crudheroesapp

import android.app.Application
import com.facebook.stetho.Stetho

class CRUDHeroesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}