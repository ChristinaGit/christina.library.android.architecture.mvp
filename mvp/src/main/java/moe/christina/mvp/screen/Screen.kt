package moe.christina.mvp.screen

import io.reactivex.Observable
import moe.christina.common.core.Event

interface Screen {
    val onScreenStateChanged: Observable<LifecycleEvent>

    open class LifecycleEvent(val type: LifecycleEventType) : Event()

    enum class LifecycleEventType {
        CREATE,
        APPEAR,
        DISAPPEAR,
        DESTROY
    }
}

