package christina.library.android.architecture.mvp.screen_view.task

import christina.library.android.architecture.mvp.screen_view.content.displayError
import christina.library.android.architecture.mvp.screen_view.content.displayProgress
import io.reactivex.Observable

inline fun <Result, Error> Observable<Result>.displayTask(
    screenView: TaskScreenView<Result, Error>,
    crossinline converter: (Throwable) -> Error
): Observable<Result> = this
    .displayError(screenView.errorScreenView, converter)
    .displayProgress(screenView.progressScreenView)
    .doOnSubscribe {
        if (screenView.visible) {
            screenView.visible = false
        }
    }
    .doOnError {
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