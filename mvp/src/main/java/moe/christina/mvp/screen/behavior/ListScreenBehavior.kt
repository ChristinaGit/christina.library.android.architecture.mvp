package moe.christina.mvp.screen.behavior

import io.reactivex.Observable

interface ListScreenBehavior<in TItem> {
    fun displayItems(items: List<TItem>?)

    fun displayItemsLoading()

    fun displayItemsRefreshing()

    fun displayItemsLoadError()

    fun displayItemsRefreshError()

    val onViewItems: Observable<ViewItemsEvent>

    enum class ViewItemsEvent {
        NEW,
        REFRESH
    }
}