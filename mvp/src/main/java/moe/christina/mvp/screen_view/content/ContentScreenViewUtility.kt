package moe.christina.mvp.screen_view.content

import io.reactivex.Observable
import moe.christina.mvp.screen_view.ScreenView

@JvmOverloads
fun <T, TError> Observable<T>.displayError(
    screenView: ContentScreenView<TError>,
    converter: ((Throwable) -> TError)? = null): Observable<T> {
    var result = this

    result = result
        .doOnSubscribe {
            if (screenView.visibility) {
                screenView.visibility = false
            }
        }
        .doOnComplete {
            if (screenView.visibility) {
                screenView.visibility = false
            }
        }

    if (converter !== null) {
        result = result.doOnError {
            if (!screenView.visibility) {
                screenView.visibility = true
            }

            screenView.display(converter(it))
        }
    }

    return result
}

fun <T> Observable<T>.displayProgress(screenView: ScreenView): Observable<T> {
    var result = this

    result = result
        .doOnSubscribe {
            if (!screenView.visibility) {
                screenView.visibility = true
            }
        }
        .doOnComplete {
            if (screenView.visibility) {
                screenView.visibility = false
            }
        }
        .doOnError {
            if (screenView.visibility) {
                screenView.visibility = false
            }
        }

    return result
}

fun <T, TProgressValue> Observable<T>.displayProgress(
    screenView: ContentScreenView<TProgressValue>,
    converter: (T) -> TProgressValue): Observable<T> {
    var result = this

    result = result.displayProgress(screenView)

    result = result.doOnNext {
        screenView.display(converter(it))
    }

    return result
}