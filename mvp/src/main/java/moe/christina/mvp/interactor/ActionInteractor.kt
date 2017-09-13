package moe.christina.mvp.interactor

import io.reactivex.Completable

interface ActionInteractor<T> {
    operator fun invoke(vararg args: T): Completable
}