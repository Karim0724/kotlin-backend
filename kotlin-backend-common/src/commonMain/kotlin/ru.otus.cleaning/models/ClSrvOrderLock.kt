package ru.otus.cleaning.models

import kotlin.jvm.JvmInline

@JvmInline
value class ClSrvOrderLock(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = ClSrvOrderLock("")
    }
}
