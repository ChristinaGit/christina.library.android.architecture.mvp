package moe.christina.mvp.presenter

interface Presenter<out TScreen> {
    val screen: TScreen
    fun bindScreen()
    fun unbindScreen()
}
