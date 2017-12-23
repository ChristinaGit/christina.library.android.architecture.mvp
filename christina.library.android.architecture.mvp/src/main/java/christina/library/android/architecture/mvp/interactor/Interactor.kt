package christina.library.android.architecture.mvp.interactor

@FunctionalInterface
interface Interactor<in Argument, out Result> {
    fun execute(argument: Argument): Result
}
