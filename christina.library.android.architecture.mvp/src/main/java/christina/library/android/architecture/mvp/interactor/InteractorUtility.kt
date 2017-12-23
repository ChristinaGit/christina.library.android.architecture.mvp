package christina.library.android.architecture.mvp.interactor

typealias ActionInteractor<Result> = Interactor<Unit, Result>

inline fun <Argument, Result> interactor(crossinline lambda: (Argument) -> Result): Interactor<Argument, Result> =
    object : Interactor<Argument, Result> {
        override fun execute(argument: Argument): Result = lambda(argument)
    }