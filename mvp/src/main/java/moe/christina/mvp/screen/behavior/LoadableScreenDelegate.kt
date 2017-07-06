package moe.christina.mvp.screen.behavior

import android.support.annotation.CallSuper
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import moe.christina.common.core.Event

open class LoadableScreenDelegate<TData> : LoadableScreenBehavior<TData> {
    var dataViewController: DataViewController? = null
    var loadDataViewController: LoadDataViewController? = null
    var dataConsumer: DataConsumer<TData>? = null

    fun setDataViewController(dataViewVisibilitySetter: (Boolean) -> Unit,
                              noDataViewVisibilitySetter: (Boolean) -> Unit) {
        dataViewController = object : DataViewController {
            override fun setDataViewVisibility(visible: Boolean) =
                dataViewVisibilitySetter(visible)

            override fun setNoDataViewVisibility(visible: Boolean) =
                noDataViewVisibilitySetter(visible)
        }
    }

    fun setLoadDataViewController(dataLoadViewVisibilitySetter: (Boolean) -> Unit,
                                  dataLoadErrorViewVisibilitySetter: (Boolean) -> Unit) {
        loadDataViewController = object : LoadDataViewController {
            override fun setDataLoadViewVisibility(visible: Boolean) =
                dataLoadViewVisibilitySetter(visible)

            override fun setDataLoadErrorViewVisibility(visible: Boolean) =
                dataLoadErrorViewVisibilitySetter(visible)
        }
    }

    fun setDataConsumer(hasDataPredicate: () -> Boolean,
                        dataSetter: (TData?) -> Unit,
                        dataEmptyPredicate: (TData?) -> Boolean) {
        dataConsumer = object : DataConsumer<TData> {
            override val hasData: Boolean
                get() = hasDataPredicate()

            override fun setData(data: TData?) = dataSetter(data)
            override fun isEmpty(data: TData?) = dataEmptyPredicate(data)
        }
    }

    final override  val onLoadData: Observable<Event>
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