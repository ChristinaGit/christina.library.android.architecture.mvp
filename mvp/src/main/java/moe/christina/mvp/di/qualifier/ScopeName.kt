package moe.christina.mvp.di.qualifier

object ScopeName {
    private const val NAME_PREFIX = "scope" + Qualifier.NAME_SEPARATOR
    const val APPLICATION = NAME_PREFIX + "application"
    const val SCREEN = NAME_PREFIX + "screen"
    const val SUBSCREEN = NAME_PREFIX + "subscreen"
}