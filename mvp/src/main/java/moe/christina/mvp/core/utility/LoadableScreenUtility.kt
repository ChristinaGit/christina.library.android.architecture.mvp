package moe.christina.mvp.core.utility

import io.reactivex.ObservableTransformer
import moe.christina.common.android.coordination.LoadingViewVisibilityCoordinator
import moe.christina.mvp.screen.behavior.LoadableScreenBehavior
import moe.christina.mvp.screen.behavior.LoadableScreenDelegate.DataViewController
import moe.christina.mvp.screen.behavior.LoadableScreenDelegate.LoadDataViewController

fun <TData> LoadableScreenBehavior<TData>.displayLoadDataTransformer()
    : ObservableTransformer<TData, TData> = ObservableTransformer {
    it.doOnSubscribe { displayLoadDataProgress() }
        .doOnNext { displayData(it) }
        .doOnError { displayLoadDataError() }
}

fun LoadingViewVisibilityCoordinator.asDataViewController() =
    object : DataViewController {
        override fun setDataViewVisibility(visible: Boolean)
            = setContentViewVisibility(visible)

        override fun setNoDataViewVisibility(visible: Boolean)
            = setNoContentViewVisibility(visible)
    }

fun LoadingViewVisibilityCoordinator.asLoadDataViewController() =
    object : LoadDataViewController {
        override fun setDataLoadViewVisibility(visible: Boolean)
            = setLoadingViewVisibility(visible)

        override fun setDataLoadErrorViewVisibility(visible: Boolean)
            = setErrorViewVisibility(visible)
    }