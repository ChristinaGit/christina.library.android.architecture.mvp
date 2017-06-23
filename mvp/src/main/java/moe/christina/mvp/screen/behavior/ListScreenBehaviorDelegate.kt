package moe.christina.mvp.screen.behavior

import android.support.annotation.CallSuper
import android.support.v4.widget.SwipeRefreshLayout
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import moe.christina.common.android.coordination.LoadingViewVisibilityCoordinator
import moe.christina.common.android.view.recycler.adapter.RecyclerViewListAdapter
import moe.christina.mvp.screen.behavior.ListScreenBehavior.ViewItemsEvent

open class ListScreenBehaviorDelegate<TItem> : ListScreenBehavior<TItem> {
    companion object {
        fun SwipeRefreshLayout.asItemRefresher(): ItemsRefresher {
            val swipeRefreshLayout = this

            return object : ItemsRefresher {
                override var isRefreshing: Boolean
                    get() = swipeRefreshLayout.isRefreshing
                    set(value) {
                        swipeRefreshLayout.isRefreshing = value
                    }
            }
        }

        fun <TItem> RecyclerViewListAdapter<TItem, *>.asItemsConsumer(): ItemsConsumer<TItem> {
            val adapter = this

            return object : ItemsConsumer<TItem> {
                override fun setItems(items: List<TItem>?) {
                    adapter.apply {
                        this.items = items
                        notifyInnerItemsChanged()
                    }
                }
            }
        }
    }

    var itemsRefresher: ItemsRefresher? = null
    var visibilityCoordinator: LoadingViewVisibilityCoordinator? = null
    var itemsConsumer: ItemsConsumer<TItem>? = null

    @CallSuper
    override fun displayItems(items: List<TItem>?) {
        itemsRefresher?.isRefreshing = false
        itemsConsumer?.setItems(items)

        visibilityCoordinator?.showContentView(items != null && !items.isEmpty())
    }

    @CallSuper
    override fun displayItemsLoading() {
        itemsRefresher?.isRefreshing = false
        visibilityCoordinator?.showLoadingView()
    }

    @CallSuper
    override fun displayItemsRefreshing() {
        itemsRefresher?.isRefreshing = true
    }

    @CallSuper
    override fun displayItemsLoadError() {
        itemsRefresher?.isRefreshing = false
        visibilityCoordinator?.showErrorView()
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

    interface ItemsRefresher {
        var isRefreshing: Boolean
    }

    interface ItemsConsumer<in TItem> {
        fun setItems(items: List<TItem>?)
    }
}