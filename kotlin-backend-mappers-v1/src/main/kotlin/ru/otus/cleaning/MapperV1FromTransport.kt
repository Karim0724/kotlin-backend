package ru.otus.cleaning

import kotlinx.datetime.Instant
import ru.otus.cleaning.api.v1.models.IRequest
import ru.otus.cleaning.api.v1.models.IResponse
import ru.otus.cleaning.api.v1.models.OrderCreateObject
import ru.otus.cleaning.api.v1.models.OrderCreateRequest
import ru.otus.cleaning.api.v1.models.OrderDebug
import ru.otus.cleaning.api.v1.models.OrderReadObject
import ru.otus.cleaning.api.v1.models.OrderReadRequest
import ru.otus.cleaning.api.v1.models.OrderRequestDebugMode
import ru.otus.cleaning.api.v1.models.OrderRequestDebugStubs
import ru.otus.cleaning.api.v1.models.OrderSearchByCompanyIdObject
import ru.otus.cleaning.api.v1.models.OrderSearchByCompanyIdRequest
import ru.otus.cleaning.api.v1.models.OrderSearchByUserIdObject
import ru.otus.cleaning.api.v1.models.OrderSearchByUserIdRequest
import ru.otus.cleaning.exception.UnknownRequestClass
import ru.otus.cleaning.models.ClSrvCommand
import ru.otus.cleaning.models.ClSrvCompanyId
import ru.otus.cleaning.models.ClSrvOrder
import ru.otus.cleaning.models.ClSrvOrderId
import ru.otus.cleaning.models.ClSrvRequestId
import ru.otus.cleaning.models.ClSrvUserId
import ru.otus.cleaning.models.ClSrvWorkMode
import ru.otus.otuskotlin.marketplace.common.stubs.ClSrvStubs

fun ClSrvContext.fromTransport(request: IRequest) = when(request) {
    is OrderCreateRequest -> fromTransport(request)
    is OrderReadRequest -> fromTransport(request)
    is OrderSearchByCompanyIdRequest -> fromTransport(request)
    is OrderSearchByUserIdRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request.javaClass)
}

fun ClSrvContext.fromTransport(request: OrderCreateRequest) {
    command = ClSrvCommand.CREATE
    requestId = request.requestId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
    orderRequest = request.order?.toInternal() ?: orderRequest
}

fun ClSrvContext.fromTransport(request: OrderReadRequest) {
    command = ClSrvCommand.READ
    requestId = request.requestId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
    orderRequest = request.order?.toInternal() ?: orderRequest
}

fun ClSrvContext.fromTransport(request: OrderSearchByCompanyIdRequest) {
    command = ClSrvCommand.SEARCH_BY_COMPANY_ID
    requestId = request.requestId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
    orderRequest = request.order?.toInternal() ?: orderRequest
}

fun ClSrvContext.fromTransport(request: OrderSearchByUserIdRequest) {
    command = ClSrvCommand.SEARCH_BY_USER_ID
    requestId = request.requestId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
    orderRequest = request.order?.toInternal() ?: orderRequest
}

private fun OrderCreateObject.toInternal() = ClSrvOrder(
    userId = userId?.takeIf { it.isNotBlank() }?.let { ClSrvUserId(id = it) } ?: ClSrvUserId.NONE,
    companyId = companyId?.takeIf { it.isNotBlank() }?.let { ClSrvCompanyId(id = it) } ?: ClSrvCompanyId.NONE,
    dateTime = dateTime?.takeIf { it.isNotBlank() }?.let { Instant.parse(it) } ?: Instant.NONE,
    address = address ?: ""
)

private fun OrderReadObject.toInternal() = ClSrvOrder(id = this.id?.let { ClSrvOrderId(it) } ?: ClSrvOrderId.NONE)

private fun OrderSearchByCompanyIdObject.toInternal() = ClSrvOrder(
    companyId = companyId?.takeIf { it.isNotBlank() }?.let { ClSrvCompanyId(id = it) } ?: ClSrvCompanyId.NONE
)

private fun OrderSearchByUserIdObject.toInternal() = ClSrvOrder(
    userId = userId?.takeIf { it.isNotBlank() }?.let { ClSrvUserId(id = it) } ?: ClSrvUserId.NONE
)

private fun IRequest?.requestId() = this?.requestId?.let { ClSrvRequestId(it) } ?: ClSrvRequestId.NONE

private fun OrderDebug?.transportToWorkMode(): ClSrvWorkMode = when (this?.mode) {
    OrderRequestDebugMode.PROD -> ClSrvWorkMode.PROD
    OrderRequestDebugMode.TEST -> ClSrvWorkMode.TEST
    OrderRequestDebugMode.STUB -> ClSrvWorkMode.STUB
    null -> ClSrvWorkMode.PROD
}

private fun OrderDebug?.transportToStubCase(): ClSrvStubs = when (this?.stub) {
    OrderRequestDebugStubs.SUCCESS -> ClSrvStubs.SUCCESS
    OrderRequestDebugStubs.NOT_FOUND -> ClSrvStubs.NOT_FOUND
    OrderRequestDebugStubs.BAD_ID -> ClSrvStubs.BAD_ID
    OrderRequestDebugStubs.BAD_TITLE -> ClSrvStubs.BAD_TITLE
    OrderRequestDebugStubs.BAD_DESCRIPTION -> ClSrvStubs.BAD_DESCRIPTION
    OrderRequestDebugStubs.BAD_VISIBILITY -> ClSrvStubs.BAD_VISIBILITY
    OrderRequestDebugStubs.CANNOT_DELETE -> ClSrvStubs.CANNOT_DELETE
    OrderRequestDebugStubs.BAD_SEARCH_STRING -> ClSrvStubs.BAD_SEARCH_STRING
    null -> ClSrvStubs.NONE
}