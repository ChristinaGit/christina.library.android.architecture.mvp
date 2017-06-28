package moe.christina.mvp.screen.behavior

import io.reactivex.Observable
import moe.christina.common.core.Event

interface LoadableScreenBehavior<in TData> {
    fun displayData(data: TData?)

    fun displayLoadDataProgress()
    fun displayLoadDataError()

    val onLoadData: Observable<Event>
}