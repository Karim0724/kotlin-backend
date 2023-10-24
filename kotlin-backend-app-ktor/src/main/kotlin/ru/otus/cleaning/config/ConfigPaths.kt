package ru.otus.cleaning.config

object ConfigPaths {
    private const val clSrvRoot = "cleaning"
    const val repository = "$clSrvRoot.repository"
}

enum class OrderDbType(val confName: String) {
    PROD("prod"), TEST("test")
}
