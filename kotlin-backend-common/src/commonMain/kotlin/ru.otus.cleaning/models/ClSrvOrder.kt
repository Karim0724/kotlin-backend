package ru.otus.cleaning.models

import kotlinx.datetime.Instant
import ru.otus.cleaning.NONE

data class ClSrvOrder(
    val userId: ClSrvUserId = ClSrvUserId.NONE,
    val companyId: ClSrvCompanyId = ClSrvCompanyId.NONE,
    val id: ClSrvOrderId = ClSrvOrderId.NONE,
    val dateTime: Instant = Instant.NONE,
    val address: String = ""
)