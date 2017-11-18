package christina.library.android.architecture.mvp.interactor

@FunctionalInterface
interface Interactor<in TArgument, out TResult> {
    fun execute(argument: TArgument): TResult
}
