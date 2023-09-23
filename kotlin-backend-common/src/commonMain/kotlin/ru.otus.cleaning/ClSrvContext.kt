package ru.otus.cleaning

import kotlinx.datetime.Instant
import ru.otus.cleaning.models.ClSrvCommand
import ru.otus.cleaning.models.ClSrvError
import ru.otus.cleaning.models.ClSrvOrder
import ru.otus.cleaning.models.ClSrvRequestId
import ru.otus.cleaning.models.ClSrvState
import ru.otus.cleaning.models.ClSrvWorkMode
import ru.otus.cleaning.repo.IOrderRepository
import ru.otus.cleaning.stubs.ClSrvStubs

data class ClSrvContext(
    var command: ClSrvCommand = ClSrvCommand.NONE,
    var state: ClSrvState = ClSrvState.NONE,
    val errors: MutableList<ClSrvError> = mutableListOf(),
    var settings: ClSrvCorSettings = ClSrvCorSettings.NONE,

    var orderRepo: IOrderRepository = IOrderRepository.NONE,
    var orderRepoRead: ClSrvOrder = ClSrvOrder(),
    var orderRepoPrepare: ClSrvOrder = ClSrvOrder(),
    var orderRepoDone: ClSrvOrder = ClSrvOrder(),
    var ordersRepoDone: MutableList<ClSrvOrder> = mutableListOf(),

    var workMode: ClSrvWorkMode = ClSrvWorkMode.PROD,
    var stubCase: ClSrvStubs = ClSrvStubs.NONE,

    var requestId: ClSrvRequestId = ClSrvRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var orderRequest: ClSrvOrder = ClSrvOrder(),
    var orderResponse: ClSrvOrder = ClSrvOrder(),
    var ordersResponse: MutableList<ClSrvOrder> = mutableListOf(),

    var orderValidating: ClSrvOrder = ClSrvOrder(),
    var orderValidated: ClSrvOrder = ClSrvOrder()
)
