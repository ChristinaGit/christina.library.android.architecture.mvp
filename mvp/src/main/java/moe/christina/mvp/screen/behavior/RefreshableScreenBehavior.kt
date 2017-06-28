package moe.christina.mvp.screen.behavior

import io.reactivex.Observable
import moe.christina.common.core.Event

interface RefreshableScreenBehavior<in TData> : LoadableScreenBehavior<TData> {
    fun displayRefreshDataProgress()
    fun displayRefreshDataError()

    val onRefreshData: Observable<Event>
}