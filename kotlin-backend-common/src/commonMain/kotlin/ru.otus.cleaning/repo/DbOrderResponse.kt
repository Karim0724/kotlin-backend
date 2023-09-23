package ru.otus.cleaning.repo

import ru.otus.cleaning.models.ClSrvError
import ru.otus.cleaning.models.ClSrvOrder

data class DbOrderResponse(
    override val data: ClSrvOrder?,
    override val isSuccess: Boolean,
    override val errors: List<ClSrvError> = emptyList()
) : IDbResponse<ClSrvOrder>