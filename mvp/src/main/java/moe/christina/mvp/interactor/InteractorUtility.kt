package moe.christina.mvp.interactor

typealias ActionInteractor<TResult> = Interactor<Unit, TResult>

fun <TResult> ActionInteractor<TResult>.execute(): TResult = execute(Unit)

operator fun <TArgument, TResult> Interactor<TArgument, TResult>.invoke(argument: TArgument): TResult = execute(argument)

operator fun <TResult> ActionInteractor<TResult>.invoke(): TResult = execute()
