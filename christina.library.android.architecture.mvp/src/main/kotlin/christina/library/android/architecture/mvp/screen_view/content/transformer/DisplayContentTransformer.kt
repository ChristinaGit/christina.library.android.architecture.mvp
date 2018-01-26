package christina.library.android.architecture.mvp.screen_view.content.transformer

import christina.common.rx.ImmutableObservableSourceTransformer
import christina.library.android.architecture.mvp.screen_view.content.ContentScreenView
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class DisplayContentTransformer<Content>(
    private val screenView: ContentScreenView<Content>,
    resettable: Boolean = false
) : ImmutableObservableSourceTransformer<Content>(
    onSubscribe = if (resettable)
        Consumer {
            if (screenView.visible) {
                screenView.visible = false
            }
        } else null,
    onNext = Consumer {
        if (!screenView.visible) {
            screenView.visible = true
        }

        screenView.display(it)
    },
    onComplete = Action {
        if (!screenView.visible) {
            screenView.visible = true
        }
    },
    onError = Consumer {
        if (screenView.visible) {
            screenView.visible = false
        }
    }
)