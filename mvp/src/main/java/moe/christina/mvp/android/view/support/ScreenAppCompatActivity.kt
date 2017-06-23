package moe.christina.mvp.android.view.support

import android.os.Bundle
import android.support.annotation.CallSuper
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import moe.christina.common.android.support.ObservableAppCompatActivity
import moe.christina.mvp.screen.Screen
import moe.christina.mvp.screen.Screen.LifecycleEvent

abstract class ScreenAppCompatActivity : ObservableAppCompatActivity(),
        Screen {
    override val onScreenStateChanged: Observable<LifecycleEvent>
        get() = onScreenStateChangedSubject.hide()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onScreenStateChangedSubject.onNext(LifecycleEvent.CREATE)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()

        onScreenStateChangedSubject.onNext(LifecycleEvent.APPEAR)
    }

    @CallSuper
    override fun onPause() {
        onScreenStateChangedSubject.onNext(LifecycleEvent.DISAPPEAR)

        super.onPause()
    }

    @CallSuper
    override fun onDestroy() {
        onScreenStateChangedSubject.onNext(LifecycleEvent.DESTROY)

        super.onDestroy()
    }

    private val onScreenStateChangedSubject: Subject<LifecycleEvent> = PublishSubject.create()
}