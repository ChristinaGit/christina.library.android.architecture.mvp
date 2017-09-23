package moe.christina.mvp.screen_view.task

import io.reactivex.Observable
import moe.christina.mvp.screen_view.content.displayError
import moe.christina.mvp.screen_view.content.displayProgress

@JvmOverloads
fun <TResult, TError> Observable<TResult>.displayTask(
    screenView: TaskScreenView<TResult, TError>,
    converter: ((Throwable) -> TError)? = null): Observable<TResult> {
    var result = this

    result = result.displayError(screenView.errorScreenView, converter)
    result = result.displayProgress(screenView.progressScreenView)

    result = result
        .doOnSubscribe {
            if (screenView.visibility) {
                screenView.visibility = false
            }
        }
        .doOnError {
            if (screenView.visibility) {
                screenView.visibility = false
            }
        }
        .doOnNext {
            if (!screenView.visibility) {
                screenView.visibility = true
            }

            screenView.display(it)
        }

    return result
}