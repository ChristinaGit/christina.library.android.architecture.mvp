package christina.library.android.architecture.mvp.screen_view.content

import christina.library.android.architecture.mvp.screen_view.ScreenView
import io.reactivex.Observable

fun <Content> Observable<Content>.displayContent(
    screenView: ContentScreenView<Content>
): Observable<Content> = this
    .doOnNext {
        if (screenView.visible) {
            screenView.visible = false
        }

        screenView.display(it)
    }

inline fun <T, Error> Observable<T>.displayError(
    screenView: ContentScreenView<Error>,
    crossinline converter: (Throwable) -> Error
): Observable<T> = this
    .doOnSubscribe {
        if (screenView.visible) {
            screenView.visible = false
        }
    }
    .doOnComplete {
        if (screenView.visible) {
            screenView.visible = false
        }
    }
    .doOnError {
        if (!screenView.visible) {
            screenView.visible = true
        }

        screenView.display(converter(it))
    }

fun <T> Observable<T>.displayProgress(
    screenView: ScreenView
): Observable<T> = this
    .doOnSubscribe {
        if (!screenView.visible) {
            screenView.visible = true
        }
    }
    .doOnComplete {
        if (screenView.visible) {
            screenView.visible = false
        }
    }
    .doOnError {
        if (screenView.visible) {
            screenView.visible = false
        }
    }

inline fun <T, TProgressValue> Observable<T>.displayProgress(
    screenView: ContentScreenView<TProgressValue>,
    crossinline converter: (T) -> TProgressValue
): Observable<T> = this
    .displayProgress(screenView)
    .doOnNext {
        screenView.display(converter(it))
    }
