package moe.christina.mvp.screen.behavior

import io.reactivex.Observable
import moe.christina.common.core.Event

interface LoadablePageScreenBehavior<in TData> : RefreshableScreenBehavior<TData> {
    fun displayDataPage(data: TData?, page: Int, lastPage: Boolean)
    fun displayLoadDataPageProgress()
    fun displayLoadDataPageError()

    val onLoadDataPage: Observable<ViewDataPageEvent>

    open class ViewDataPageEvent(val page: Int) : Event()
}
