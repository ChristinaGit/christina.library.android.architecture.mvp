package christina.library.android.architecture.mvp.screen_view.task

import christina.library.android.architecture.mvp.screen_view.ScreenView
import christina.library.android.architecture.mvp.screen_view.content.ContentScreenView

interface TaskScreenView<in Result, in Error> : ContentScreenView<Result> {
    val progressScreenView: ScreenView
    val errorScreenView: ContentScreenView<Error>
}