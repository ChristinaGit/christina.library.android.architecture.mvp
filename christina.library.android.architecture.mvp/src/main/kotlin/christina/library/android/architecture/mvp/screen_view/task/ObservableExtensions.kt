package christina.library.android.architecture.mvp.screen_view.task

import christina.library.android.architecture.mvp.screen_view.content.transformer.displayContent
import christina.library.android.architecture.mvp.screen_view.content.transformer.displayError
import christina.library.android.architecture.mvp.screen_view.content.transformer.displayProgress
import io.reactivex.Observable

fun <Result, Error> Observable<Result>.displayTask(
    screenView: IndeterminateTaskScreenView<Result, Error>,
    errorConverter: (Throwable) -> Error,
    resettableContent: Boolean = false,
    continuousProgress: Boolean = false
): Observable<Result> = this
    .displayContent(screenView, resettableContent)
    .displayProgress(screenView.progressScreenView, continuousProgress)
    .displayError(screenView.errorScreenView, errorConverter)