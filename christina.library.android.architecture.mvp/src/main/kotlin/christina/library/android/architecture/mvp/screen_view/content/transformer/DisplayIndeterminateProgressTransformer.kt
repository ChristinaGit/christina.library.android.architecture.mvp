package christina.library.android.architecture.mvp.screen_view.content.transformer

import christina.common.rx.ImmutableObservableSourceTransformer
import christina.library.android.architecture.mvp.screen_view.ScreenView
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class DisplayIndeterminateProgressTransformer<Content>(
    private val screenView: ScreenView,
    continuous: Boolean = false
) : ImmutableObservableSourceTransformer<Content>(
    onSubscribe = Consumer {
        if (!screenView.visible) {
            screenView.visible = true
        }
    },
    onNext = if (!continuous) Consumer {
        if (screenView.visible) {
            screenView.visible = false
        }
    } else null,
    onComplete = Action {
        if (screenView.visible) {
            screenView.visible = false
        }
    },
    onError = Consumer {
        if (screenView.visible) {
            screenView.visible = false
        }
    }
)