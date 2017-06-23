package moe.christina.mvp.di.qualifier

object ScopeNames {
    @JvmStatic
    private val NAME_PREFIX = "scope:"
    @JvmStatic
    val application = NAME_PREFIX + "application"
    @JvmStatic
    val screen = NAME_PREFIX + "screen"
    @JvmStatic
    val subscreen = NAME_PREFIX + "subscreen"
}