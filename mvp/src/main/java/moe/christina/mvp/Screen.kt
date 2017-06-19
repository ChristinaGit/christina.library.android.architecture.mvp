package moe.christina.mvp

import io.reactivex.Observable

enum class ScreenLifecycleEvent {
    CREATE,
    APPEAR,
    DISAPPEAR,
    DESTROY
}

interface Screen {
    val onScreenStateChanged: Observable<ScreenLifecycleEvent>
}