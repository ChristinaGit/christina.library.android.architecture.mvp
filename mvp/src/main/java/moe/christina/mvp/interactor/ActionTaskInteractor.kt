package moe.christina.mvp.interactor

import io.reactivex.Completable

interface ActionTaskInteractor {
    operator fun invoke(): Completable
}