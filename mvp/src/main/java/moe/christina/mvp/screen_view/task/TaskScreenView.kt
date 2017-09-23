package moe.christina.mvp.screen_view.task

import moe.christina.mvp.screen_view.ScreenView
import moe.christina.mvp.screen_view.content.ContentScreenView

interface TaskScreenView<in TResult, in TError> : ContentScreenView<TResult> {
    val progressScreenView: ScreenView
    val errorScreenView: ContentScreenView<TError>
}