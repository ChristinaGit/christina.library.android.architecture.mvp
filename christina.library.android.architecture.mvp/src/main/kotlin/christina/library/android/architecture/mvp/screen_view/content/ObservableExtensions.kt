package christina.library.android.architecture.mvp.screen_view.content

import christina.library.android.architecture.mvp.screen_view.ScreenView
import io.reactivex.Observable

fun <Content> Observable<Content>.displayContent(
    screenView: ContentScreenView<Content>
): Observable<Content> = this
    .doOnSubscribe {
        if (screenView.visible) {
            screenView.visible = false
        }
    }
    .doOnNext {
        if (!screenView.visible) {
            screenView.visible = true
        }

        screenView.display(it)
    }
    .doOnComplete {
        if (!screenView.visible) {
            screenView.visible = true
        }
    }
    .doOnError {
        if (screenView.visible) {
            screenView.visible = false
        }
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
    .doOnError {
        if (!screenView.visible) {
            screenView.visible = true
        }

        screenView.display(converter(it))
    }

fun <T> Observable<T>.displayInitialProgress(
    screenView: ScreenView
): Observable<T> = this
    .doOnSubscribe {
        if (!screenView.visible) {
            screenView.visible = true
        }
    }
    .doOnNext {
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
        if (screenView.visible) {
            screenView.visible = false
        }
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

inline fun <T, Progress> Observable<T>.displayProgress(
    screenView: ContentScreenView<Progress>,
    crossinline converter: (T) -> Progress
): Observable<T> = this
    .displayProgress(screenView)
    .doOnNext {
        screenView.display(converter(it))
    }
