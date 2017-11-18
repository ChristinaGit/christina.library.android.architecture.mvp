package christina.library.android.architecture.mvp.di.qualifier

object ScopeName {
    private const val NAME_PREFIX = "scope" + Qualifier.NAME_SEPARATOR
    const val APPLICATION = NAME_PREFIX + "application"
    const val ACTIVITY = NAME_PREFIX + "activity"
    const val FRAGMENT = NAME_PREFIX + "fragment"
}