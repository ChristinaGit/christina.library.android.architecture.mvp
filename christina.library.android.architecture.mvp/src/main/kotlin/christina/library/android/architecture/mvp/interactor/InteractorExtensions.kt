package christina.library.android.architecture.mvp.interactor

fun <Result> Interactor<Unit, Result>.execute(): Result = execute(Unit)

operator fun <Argument, Result> Interactor<Argument, Result>.invoke(argument: Argument): Result = execute(argument)

operator fun <Result> Interactor<Unit, Result>.invoke(): Result = execute()