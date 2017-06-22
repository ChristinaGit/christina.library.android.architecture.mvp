package moe.christina.mvp.screen

import io.reactivex.Observable

interface Screen {
    val onScreenStateChanged: Observable<ScreenLifecycleEvent>
}

enum class ScreenLifecycleEvent {
    CREATE,
    APPEAR,
    DISAPPEAR,
    DESTROY
}
