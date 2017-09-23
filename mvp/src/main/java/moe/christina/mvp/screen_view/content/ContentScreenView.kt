package moe.christina.mvp.screen_view.content

import moe.christina.mvp.screen_view.ScreenView

interface ContentScreenView<in TContent> : ScreenView {
    fun display(content: TContent)
}