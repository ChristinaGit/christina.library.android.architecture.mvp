package moe.christina.mvp.core.utility

import android.support.v4.widget.SwipeRefreshLayout
import io.reactivex.ObservableTransformer
import moe.christina.mvp.screen.behavior.RefreshableScreenBehavior
import moe.christina.mvp.screen.behavior.RefreshableScreenDelegate.RefreshDataViewController

fun <TData> RefreshableScreenBehavior<TData>.displayRefreshDataTransformer(
    errorConverter: ((Throwable) -> String?)? = { it.message })
    : ObservableTransformer<TData, TData> = ObservableTransformer {
    it.doOnSubscribe { displayRefreshDataProgress() }
        .doOnNext { displayData(it) }
        .doOnError { displayRefreshDataError(errorConverter?.invoke(it)) }
}

fun SwipeRefreshLayout.asRefreshDataViewController(
    errorViewVisibilitySetter: ((Boolean) -> Unit)? = null,
    messageSetter: ((String?) -> Unit)?) =
    object : RefreshDataViewController {
        override var isDataRefreshEnabled: Boolean
            get() = isEnabled
            set(value) {
                isEnabled = value
            }

        override fun setDataRefreshViewVisibility(visible: Boolean) {
            isRefreshing = visible
        }

        override fun setDataRefreshErrorViewVisibility(visible: Boolean) {
            errorViewVisibilitySetter?.invoke(visible)
        }

        override fun setDataRefreshErrorMessage(message: String?) {
            messageSetter?.invoke(message)
        }
    }