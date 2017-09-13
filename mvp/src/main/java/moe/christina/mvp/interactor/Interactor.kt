package moe.christina.mvp.interactor

import io.reactivex.Observable

interface Interactor<T, R> {
    operator fun invoke(vararg args: T): Observable<R>
}
