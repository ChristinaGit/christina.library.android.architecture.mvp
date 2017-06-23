package moe.christina.mvp.screen

import io.reactivex.Observable

interface Screen {
    val onScreenStateChanged: Observable<LifecycleEvent>

    enum class LifecycleEvent {
        CREATE,
        APPEAR,
        DISAPPEAR,
        DESTROY
    }
}

