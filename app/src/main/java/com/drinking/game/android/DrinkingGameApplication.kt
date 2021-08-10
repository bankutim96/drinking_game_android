package com.drinking.game.android

import android.app.Application
import com.drinking.game.android.ui.UIModule

class DrinkingGameApplication: Application() {
    lateinit var injector: DrinkingGameApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerDrinkingGameApplicationComponent.builder().uIModule(UIModule(this)).build()
    }
}