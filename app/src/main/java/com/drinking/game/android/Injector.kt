package com.drinking.game.android

import android.app.Activity

val Activity.injector: DrinkingGameApplicationComponent
    get() {
        return (this.applicationContext as DrinkingGameApplication).injector
    }