package moe.christina.mvp.screen.behavior

import android.support.annotation.CallSuper
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import moe.christina.common.core.Event

open class RefreshableScreenDelegate<TData> : LoadableScreenDelegate<TData>(), RefreshableScreenBehavior<TData> {
    var refreshDataViewController: RefreshDataViewController? = null

    final override val onRefreshData: Observable<Event>
        get() = onRefreshDataSubject.hide()

    @CallSuper
    override fun displayData(data: TData?) {
        super.displayData(data)

        refreshDataViewController?.apply {
            isDataRefreshEnabled = true
            setDataRefreshViewVisibility(false)
            setDataRefreshErrorViewVisibility(false)
        }
    }

    @CallSuper
    override fun displayLoadDataProgress() {
        super.displayLoadDataProgress()

        refreshDataViewController?.apply {
            isDataRefreshEnabled = false
            setDataRefreshViewVisibility(false)
            setDataRefreshErrorViewVisibility(false)
        }
    }

    @CallSuper
    override fun displayLoadDataError(message: String?) {
        super.displayLoadDataError(message)

        refreshDataViewController?.apply {
            isDataRefreshEnabled = true
            setDataRefreshViewVisibility(false)
            setDataRefreshErrorViewVisibility(false)
        }
    }

    @CallSuper
    override fun displayRefreshDataProgress() {
        refreshDataViewController?.apply {
            setDataRefreshViewVisibility(true)
            setDataRefreshErrorViewVisibility(false)
        }
    }

    @CallSuper
    override fun displayRefreshDataError(message: String?) {
        refreshDataViewController?.apply {
            setDataRefreshErrorMessage(message)
            isDataRefreshEnabled = true
            setDataRefreshViewVisibility(false)
            setDataRefreshErrorViewVisibility(true)
        }
    }

    @CallSuper
    override fun resetState() {
        super.resetState()

        resetRefreshDataState()
    }

    fun resetRefreshDataState() {
        refreshDataViewController?.apply {
            isDataRefreshEnabled = true
            setDataRefreshViewVisibility(false)
            setDataRefreshErrorViewVisibility(false)
        }
    }

    fun riseRefreshDataEvent() = onRefreshDataSubject.onNext(Event.empty)

    private val onRefreshDataSubject: PublishSubject<Event> = PublishSubject.create()

    interface RefreshDataViewController {
        var isDataRefreshEnabled: Boolean
        fun setDataRefreshViewVisibility(visible: Boolean)
        fun setDataRefreshErrorViewVisibility(visible: Boolean)
        fun setDataRefreshErrorMessage(message: String?) {}
    }
}