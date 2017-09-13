package moe.christina.mvp.interactor

import io.reactivex.Observable

interface TaskInteractor<R> {
    operator fun invoke(): Observable<R>
}