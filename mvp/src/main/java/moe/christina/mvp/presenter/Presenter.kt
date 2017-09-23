package moe.christina.mvp.presenter

import android.support.annotation.CallSuper

abstract class Presenter<out TScreen>(
    protected val screen: TScreen
) : ScreenObserver {
    final override fun subscribe() {
        if (subscribed) {
            throw IllegalStateException("Presenter is already subscribed.")
        }

        subscribed = true

        onSubscribe()
    }

    final override fun unsubscribe() {
        if (!subscribed) {
            throw IllegalStateException("Presenter is not subscribed.")
        }

        subscribed = false
        onUnsubscribe()
    }

    @CallSuper
    protected open fun onSubscribe() {
    }

    @CallSuper
    protected open fun onUnsubscribe() {
    }

    private var subscribed = false
}