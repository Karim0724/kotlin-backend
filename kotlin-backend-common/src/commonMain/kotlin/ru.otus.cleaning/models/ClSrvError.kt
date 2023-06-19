package ru.otus.cleaning.models

data class ClSrvError(
    val code: String = "",
    val group: String = "",
    val field: String = "",
    val message: String = "",
    val exception: Throwable? = null,
)
