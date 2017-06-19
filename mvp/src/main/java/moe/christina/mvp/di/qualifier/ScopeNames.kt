package moe.christina.mvp.di.qualifier

object ScopeNames {
    private val NAME_PREFIX = "scope:"

    val application = NAME_PREFIX + "application"
    val screen = NAME_PREFIX + "screen"
    val subscreen = NAME_PREFIX + "subscreen"
}