package ru.otus.cleaning.models

import kotlinx.datetime.Instant
import ru.otus.cleaning.NONE

data class ClSrvOrder(
    val clSrvUserId: ClSrvUserId = ClSrvUserId.NONE,
    val clSrvCompanyId: ClSrvCompanyId = ClSrvCompanyId.NONE,
    val clSrvOrderId: ClSrvOrderId = ClSrvOrderId.NONE,
    val clSrvDateTime: Instant = Instant.NONE,
    val clSrvAddress: String = ""
)