package moe.christina.mvp.screen.behavior

import io.reactivex.Observable

interface PageListScreenBehavior<in TItem> : ListScreenBehavior<TItem> {
    fun displayItemsPage(items: List<TItem>?, page: Int, lastPage: Boolean)

    fun displayItemsPageLoading()

    fun displayItemsPageLoadError()

    val onViewItemsPage: Observable<Int>
}
