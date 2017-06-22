package moe.christina.mvp.presenter

import moe.christina.mvp.screen.Screen

interface Presenter<in TScreen : Screen> {
    fun bindScreen(newScreen: TScreen)

    fun unbindScreen()
}
