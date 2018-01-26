package christina.library.android.architecture.mvp.screen_view.content.transformer

import christina.library.android.architecture.mvp.screen_view.ScreenView
import christina.library.android.architecture.mvp.screen_view.content.ContentScreenView
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

//region Display content

@JvmOverloads
fun Completable.displayContent(
    screenView: ContentScreenView<*>,
    resettable: Boolean = false
): Completable = compose(DisplayContentTransformer(screenView, resettable))

@JvmOverloads
fun <Content> Single<Content>.displayContent(
    screenView: ContentScreenView<Content>,
    resettable: Boolean = false
): Single<Content> = compose(DisplayContentTransformer(screenView, resettable))

@JvmOverloads
fun <Content> Maybe<Content>.displayContent(
    screenView: ContentScreenView<Content>,
    resettable: Boolean = false
): Maybe<Content> = compose(DisplayContentTransformer(screenView, resettable))

@JvmOverloads
fun <Content> Observable<Content>.displayContent(
    screenView: ContentScreenView<Content>,
    resettable: Boolean = false
): Observable<Content> = compose(DisplayContentTransformer(screenView, resettable))

@JvmOverloads
fun <Content> Flowable<Content>.displayContent(
    screenView: ContentScreenView<Content>,
    resettable: Boolean = false
): Flowable<Content> = compose(DisplayContentTransformer(screenView, resettable))

//endregion Display content

//region Display error

fun <Error> Completable.displayError(
    screenView: ContentScreenView<Error>,
    converter: (Throwable) -> Error
): Completable = compose(DisplayErrorTransformer<Nothing, Error>(screenView, converter))

fun <Content, Error> Single<Content>.displayError(
    screenView: ContentScreenView<Error>,
    converter: (Throwable) -> Error
): Single<Content> = compose(DisplayErrorTransformer(screenView, converter))

fun <Content, Error> Maybe<Content>.displayError(
    screenView: ContentScreenView<Error>,
    converter: (Throwable) -> Error
): Maybe<Content> = compose(DisplayErrorTransformer(screenView, converter))

fun <Content, Error> Observable<Content>.displayError(
    screenView: ContentScreenView<Error>,
    converter: (Throwable) -> Error
): Observable<Content> = compose(DisplayErrorTransformer(screenView, converter))

fun <Content, Error> Flowable<Content>.displayError(
    screenView: ContentScreenView<Error>,
    converter: (Throwable) -> Error
): Flowable<Content> = compose(DisplayErrorTransformer(screenView, converter))

//endregion Display error

//region Display progress

@JvmOverloads
fun Completable.displayProgress(
    screenView: ScreenView,
    continuous: Boolean = false
): Completable = compose(DisplayIndeterminateProgressTransformer<Nothing>(screenView, continuous))

@JvmOverloads
fun <Content> Single<Content>.displayProgress(
    screenView: ScreenView,
    continuous: Boolean = false
): Single<Content> = compose(DisplayIndeterminateProgressTransformer(screenView, continuous))

@JvmOverloads
fun <Content> Maybe<Content>.displayProgress(
    screenView: ScreenView,
    continuous: Boolean = false
): Maybe<Content> = compose(DisplayIndeterminateProgressTransformer(screenView, continuous))

@JvmOverloads
fun <Content> Observable<Content>.displayProgress(
    screenView: ScreenView,
    continuous: Boolean = false
): Observable<Content> = compose(DisplayIndeterminateProgressTransformer(screenView, continuous))

@JvmOverloads
fun <Content> Flowable<Content>.displayProgress(
    screenView: ScreenView,
    continuous: Boolean = false
): Flowable<Content> = compose(DisplayIndeterminateProgressTransformer(screenView, continuous))

//endregion Display progress
