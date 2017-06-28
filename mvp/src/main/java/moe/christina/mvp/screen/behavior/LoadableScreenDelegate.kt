package moe.christina.mvp.screen.behavior

import android.support.annotation.CallSuper
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import moe.christina.common.core.Event

open class LoadableScreenDelegate<TData> : LoadableScreenBehavior<TData> {
    var dataViewController: DataViewController? = null
    var loadDataViewController: LoadDataViewController? = null
    var dataConsumer: DataConsumer<TData>? = null

    override final val onLoadData: Observable<Event>
        get() = onLoadDataSubject.hide()

    @CallSuper
    override fun displayData(data: TData?) {
        dataConsumer?.setData(data)
        dataViewController?.apply {
            val hasContent = !(dataConsumer?.isEmpty(data) ?: false)
            setDataViewVisibility(hasContent)
            setNoDataViewVisibility(!hasContent)
        }
        loadDataViewController?.apply {
            setDataLoadViewVisibility(false)
            setDataLoadErrorViewVisibility(false)
        }
    }

    @CallSuper
    override fun displayLoadDataProgress() {
        dataViewController?.apply {
            setDataViewVisibility(false)
            setNoDataViewVisibility(false)
        }
        loadDataViewController?.apply {
            setDataLoadViewVisibility(true)
            setDataLoadErrorViewVisibility(false)
        }
    }

    @CallSuper
    override fun displayLoadDataError() {
        dataViewController?.apply {
            setDataViewVisibility(false)
            setNoDataViewVisibility(false)
        }
        loadDataViewController?.apply {
            setDataLoadViewVisibility(false)
            setDataLoadErrorViewVisibility(true)
        }
    }

    @CallSuper
    open fun resetState() {
        resetLoadDataState()
    }

    fun resetLoadDataState() {
        val hasData = dataConsumer?.hasData ?: false
        dataViewController?.apply {
            setDataViewVisibility(hasData)
            setNoDataViewVisibility(!hasData)
        }
        loadDataViewController?.apply {
            setDataLoadViewVisibility(false)
            setDataLoadErrorViewVisibility(false)
        }
    }

    fun riseLoadDataEvent() = onLoadDataSubject.onNext(Event.empty)

    private val onLoadDataSubject: PublishSubject<Event> = PublishSubject.create()

    interface DataViewController {
        fun setDataViewVisibility(visible: Boolean)
        fun setNoDataViewVisibility(visible: Boolean)
    }

    interface LoadDataViewController {
        fun setDataLoadViewVisibility(visible: Boolean)
        fun setDataLoadErrorViewVisibility(visible: Boolean)
    }

    interface DataConsumer<in TData> {
        val hasData: Boolean
        fun setData(data: TData?)
        fun isEmpty(data: TData?): Boolean
    }
}