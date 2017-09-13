package moe.christina.mvp.presenter

import android.support.annotation.CallSuper

abstract class BasePresenter<out TScreen>
(
    final override val screen: TScreen
) : Presenter<TScreen> {
    final override fun bindScreen() {
        onBindScreen()
    }

    final override fun unbindScreen() {
        onUnbindScreen()
    }

    @CallSuper
    protected open fun onBindScreen() {
    }

    @CallSuper
    protected open fun onUnbindScreen() {
    }
}