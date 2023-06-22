package ru.otus.cleaning

import kotlinx.datetime.Instant
import ru.otus.cleaning.models.ClSrvCommand
import ru.otus.cleaning.models.ClSrvError
import ru.otus.cleaning.models.ClSrvOrder
import ru.otus.cleaning.models.ClSrvRequestId
import ru.otus.cleaning.models.ClSrvState
import ru.otus.cleaning.models.ClSrvWorkMode
import ru.otus.otuskotlin.marketplace.common.stubs.ClSrvStubs

data class ClSrvContext(
    var command: ClSrvCommand = ClSrvCommand.NONE,
    var state: ClSrvState = ClSrvState.NONE,
    val errors: MutableList<ClSrvError> = mutableListOf(),

    var workMode: ClSrvWorkMode = ClSrvWorkMode.PROD,
    var stubCase: ClSrvStubs = ClSrvStubs.NONE,

    var requestId: ClSrvRequestId = ClSrvRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var orderRequest: ClSrvOrder = ClSrvOrder(),
    var orderResponse: ClSrvOrder = ClSrvOrder(),
    val ordersResponse: MutableList<ClSrvOrder> = mutableListOf()
)
