package moe.christina.mvp.core.utility

import android.support.v4.widget.SwipeRefreshLayout
import io.reactivex.ObservableTransformer
import moe.christina.mvp.screen.behavior.RefreshableScreenBehavior
import moe.christina.mvp.screen.behavior.RefreshableScreenDelegate

fun <TData> RefreshableScreenBehavior<TData>.displayRefreshDataTransformer()
        : ObservableTransformer<TData, TData> = ObservableTransformer {
    it.doOnSubscribe { displayRefreshDataProgress() }
            .doOnNext { displayData(it) }
            .doOnError { displayRefreshDataError() }
}

fun SwipeRefreshLayout.asRefreshDataViewController(errorViewController: ((Boolean) -> Unit)? = null) =
        object : RefreshableScreenDelegate.RefreshDataViewController {
            override var isDataRefreshEnabled: Boolean
                get() = isEnabled
                set(value) {
                    isEnabled = value
                }

            override fun setDataRefreshViewVisibility(visible: Boolean) {
                isRefreshing = visible
            }

            override fun setDataRefreshErrorViewVisibility(visible: Boolean) {
                errorViewController?.invoke(visible)
            }
        }