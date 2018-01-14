package christina.library.android.architecture.mvp.screen_view.task

import christina.library.android.architecture.mvp.screen_view.content.displayContent
import christina.library.android.architecture.mvp.screen_view.content.displayError
import christina.library.android.architecture.mvp.screen_view.content.displayInitialProgress
import christina.library.android.architecture.mvp.screen_view.content.displayProgress
import io.reactivex.Observable

inline fun <Result, Error> Observable<Result>.displayTask(
    screenView: InitialTaskScreenView<Result, Error>,
    crossinline errorConverter: (Throwable) -> Error
): Observable<Result> = this
    .displayError(screenView.errorScreenView, errorConverter)
    .displayInitialProgress(screenView.progressScreenView)
    .displayContent(screenView)

inline fun <Result, Progress, Error> Observable<Result>.displayTask(
    screenView: TaskScreenView<Result, Progress, Error>,
    crossinline progressConverter: (Result) -> Progress,
    crossinline errorConverter: (Throwable) -> Error
): Observable<Result> = this
    .displayError(screenView.errorScreenView, errorConverter)
    .displayProgress(screenView.progressScreenView, progressConverter)
    .displayContent(screenView)