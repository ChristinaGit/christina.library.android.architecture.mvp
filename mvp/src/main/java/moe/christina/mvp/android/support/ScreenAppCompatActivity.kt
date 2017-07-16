package moe.christina.mvp.android.support

import android.os.Bundle
import android.support.annotation.CallSuper
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import moe.christina.common.android.support.ObservableAppCompatActivity
import moe.christina.mvp.screen.Screen
import moe.christina.mvp.screen.Screen.LifecycleEvent
import moe.christina.mvp.screen.Screen.LifecycleEventType

abstract class ScreenAppCompatActivity : ObservableAppCompatActivity(), Screen {
    final override val onScreenStateChanged: Observable<LifecycleEvent>
        get() = onScreenStateChangedSubject.hide()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        riseOnScreenStateChangedEvent(LifecycleEventType.CREATE)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()

        riseOnScreenStateChangedEvent(LifecycleEventType.APPEAR)
    }

    @CallSuper
    override fun onPause() {
        riseOnScreenStateChangedEvent(LifecycleEventType.DISAPPEAR)

        super.onPause()
    }

    @CallSuper
    override fun onDestroy() {
        riseOnScreenStateChangedEvent(LifecycleEventType.DESTROY)

        super.onDestroy()
    }

    private fun riseOnScreenStateChangedEvent(eventType: LifecycleEventType) =
        riseOnScreenStateChangedEvent(LifecycleEvent(eventType))

    private fun riseOnScreenStateChangedEvent(event: LifecycleEvent) =
        onScreenStateChangedSubject.onNext(event)

    private val onScreenStateChangedSubject: Subject<LifecycleEvent> = PublishSubject.create()
}