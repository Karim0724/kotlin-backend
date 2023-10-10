package ru.otus.cleaning.repo

import ru.otus.cleaning.models.ClSrvError
import ru.otus.cleaning.models.ClSrvOrder

data class DbOrdersResponse(
    override val data: List<ClSrvOrder>?,
    override val errors: List<ClSrvError> = emptyList(),
    override val isSuccess: Boolean
) : IDbResponse<List<ClSrvOrder>> {
    companion object {
        fun error(error: ClSrvError) = DbOrdersResponse(null, listOf(error), false,)
    }
}