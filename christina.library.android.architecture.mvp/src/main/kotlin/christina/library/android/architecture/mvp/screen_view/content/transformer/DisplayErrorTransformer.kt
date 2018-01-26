package christina.library.android.architecture.mvp.screen_view.content.transformer

import christina.common.rx.ImmutableObservableSourceTransformer
import christina.library.android.architecture.mvp.screen_view.content.ContentScreenView
import io.reactivex.functions.Consumer

class DisplayErrorTransformer<Stream, Error>(
    private val screenView: ContentScreenView<Error>,
    converter: (Throwable) -> Error
) : ImmutableObservableSourceTransformer<Stream>(
    onSubscribe = Consumer {
        if (screenView.visible) {
            screenView.visible = false
        }
    },
    onNext = null,
    onComplete = null,
    onError = Consumer {
        if (!screenView.visible) {
            screenView.visible = true
        }

        screenView.display(converter(it))
    }
)