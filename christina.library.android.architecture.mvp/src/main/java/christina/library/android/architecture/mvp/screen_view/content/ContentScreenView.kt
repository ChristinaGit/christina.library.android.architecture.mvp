package christina.library.android.architecture.mvp.screen_view.content

import christina.library.android.architecture.mvp.screen_view.ScreenView

interface ContentScreenView<in Content> : ScreenView {
    fun display(content: Content)
}