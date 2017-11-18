package christina.library.android.architecture.mvp.interactor

fun <TResult> Interactor<Unit, TResult>.execute(): TResult = execute(Unit)

operator fun <TArgument, TResult> Interactor<TArgument, TResult>.invoke(argument: TArgument): TResult = execute(argument)

operator fun <TResult> Interactor<Unit, TResult>.invoke(): TResult = execute()