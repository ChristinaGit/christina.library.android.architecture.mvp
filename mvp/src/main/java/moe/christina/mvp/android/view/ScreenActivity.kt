package moe.christina.mvp.android.view

import android.os.Bundle
import android.support.annotation.CallSuper
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import moe.christina.common.android.ObservableActivity
import moe.christina.mvp.Screen
import moe.christina.mvp.ScreenLifecycleEvent

abstract class ScreenActivity : ObservableActivity(),
        Screen {
    override val onScreenStateChanged: Observable<ScreenLifecycleEvent>
        get() = onScreenStateChangedSubject.hide()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onScreenStateChangedSubject.onNext(ScreenLifecycleEvent.CREATE)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()

        onScreenStateChangedSubject.onNext(ScreenLifecycleEvent.APPEAR)
    }

    @CallSuper
    override fun onPause() {
        onScreenStateChangedSubject.onNext(ScreenLifecycleEvent.DISAPPEAR)

        super.onPause()
    }

    @CallSuper
    override fun onDestroy() {
        onScreenStateChangedSubject.onNext(ScreenLifecycleEvent.DESTROY)

        super.onDestroy()
    }

    private val onScreenStateChangedSubject: Subject<ScreenLifecycleEvent>
            = PublishSubject.create()
}