package ru.otus.cleaning.repo

import ru.otus.cleaning.models.ClSrvError

interface IDbResponse<T> {
    val data: T?
    val isSuccess: Boolean
    val errors: List<ClSrvError>
}
