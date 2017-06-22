package moe.christina.mvp.screen.behavior

import android.support.annotation.CallSuper
import android.support.v4.widget.SwipeRefreshLayout
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import moe.christina.common.android.coordination.LoadingViewVisibilityCoordinator

interface ListScreenBehavior<in TItem> {
    fun displayItems(items: List<TItem>?)

    fun displayItemsLoading()

    fun displayItemsRefreshing()

    fun displayItemsLoadError()

    fun displayItemsRefreshError()

    val onViewItems: Observable<ViewItemsEvent>
}

enum class ViewItemsEvent {
    NEW,
    REFRESH
}

open class ListScreenBehaviorDelegate<TItem> : ListScreenBehavior<TItem> {
    var itemsRefresher: ItemsRefresher? = null
    var visibilityCoordinator: LoadingViewVisibilityCoordinator? = null
    var itemsConsumer: ItemsConsumer<TItem>? = null

    @CallSuper
    override fun displayItems(items: List<TItem>?) {
        itemsRefresher?.isRefreshing = false
        itemsConsumer?.setItems(items)

        visibilityCoordinator?.showContent(items != null && !items.isEmpty())
    }

    @CallSuper
    override fun displayItemsLoading() {
        itemsRefresher?.isRefreshing = false
        visibilityCoordinator?.showLoading()
    }

    @CallSuper
    override fun displayItemsRefreshing() {
        itemsRefresher?.isRefreshing = true
    }

    @CallSuper
    override fun displayItemsLoadError() {
        itemsRefresher?.isRefreshing = false
        visibilityCoordinator?.showError()
    }

    @CallSuper
    override fun displayItemsRefreshError() {
        itemsRefresher?.isRefreshing = false
    }

    fun riseViewItemsEvent(event: ViewItemsEvent) {
        onViewItemsSubject.onNext(event)
    }

    override final val onViewItems: Observable<ViewItemsEvent>
        get() = onViewItemsSubject.hide()

    private val onViewItemsSubject: PublishSubject<ViewItemsEvent> = PublishSubject.create()

    companion object ItemsRefresherFactory {
        fun SwipeRefreshLayout.asItemRefresher(): ItemsRefresher {
            val swipeRefreshLayout = this;

            return object : ItemsRefresher {
                override var isRefreshing: Boolean
                    get() = swipeRefreshLayout.isRefreshing
                    set(value) {
                        swipeRefreshLayout.isRefreshing = value
                    }
            }
        }
    }

    interface ItemsRefresher {
        var isRefreshing: Boolean
    }

    interface ItemsConsumer<in TItem> {
        fun setItems(items: List<TItem>?)
    }
}