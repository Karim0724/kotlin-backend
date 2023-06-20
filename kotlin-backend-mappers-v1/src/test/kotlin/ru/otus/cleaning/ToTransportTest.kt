package ru.otus.cleaning

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.Test
import ru.otus.cleaning.api.v1.models.OrderCreateResponse
import ru.otus.cleaning.api.v1.models.OrderReadResponse
import ru.otus.cleaning.api.v1.models.OrderSearchByCompanyIdResponse
import ru.otus.cleaning.api.v1.models.OrderSearchByUserIdResponse
import ru.otus.cleaning.models.ClSrvCommand
import ru.otus.cleaning.models.ClSrvCompanyId
import ru.otus.cleaning.models.ClSrvOrder
import ru.otus.cleaning.models.ClSrvOrderId
import ru.otus.cleaning.models.ClSrvRequestId
import ru.otus.cleaning.models.ClSrvState
import ru.otus.cleaning.models.ClSrvUserId
import ru.otus.cleaning.models.ClSrvWorkMode
import ru.otus.otuskotlin.marketplace.common.stubs.ClSrvStubs
import java.util.*
import kotlin.test.assertEquals

class ToTransportTest {
    @Test
    fun toTransportCreate() {
        // given
        val context = ClSrvContext(
            command = ClSrvCommand.CREATE,
            state = ClSrvState.RUNNING,
            errors = mutableListOf(),
            workMode = ClSrvWorkMode.PROD,
            stubCase = ClSrvStubs.SUCCESS,
            requestId = ClSrvRequestId(id = UUID.randomUUID().toString()),
            timeStart = Clock.System.now(),
            orderResponse = ClSrvOrder(
                userId = ClSrvUserId(id = "12"),
                companyId = ClSrvCompanyId(id = "12"),
                id = ClSrvOrderId(id = "12"),
                dateTime = Instant.parse("2023-06-03T10:15:30Z"),
                address = "Адрес"
            )
        )

        // when
        val createTransport = context.toTransport() as OrderCreateResponse

        // then
        assertEquals(expected = context.requestId.asString(), actual = createTransport.requestId)
        assertEquals(expected = "create", actual = createTransport.responseType)
        assertEquals(expected = context.orderResponse.userId.asString(), actual = createTransport.order!!.userId)
        assertEquals(expected = context.orderResponse.companyId.asString(), actual = createTransport.order!!.companyId)
        assertEquals(expected = context.orderResponse.address, actual = createTransport.order!!.address)
        assertEquals(expected = context.orderResponse.dateTime.toString(), actual = createTransport.order!!.dateTime)
    }

    @Test
    fun toTransportRead() {
        // given
        val context = ClSrvContext(
            command = ClSrvCommand.READ,
            state = ClSrvState.RUNNING,
            errors = mutableListOf(),
            workMode = ClSrvWorkMode.PROD,
            stubCase = ClSrvStubs.SUCCESS,
            requestId = ClSrvRequestId(id = UUID.randomUUID().toString()),
            timeStart = Clock.System.now(),
            orderResponse = ClSrvOrder(
                userId = ClSrvUserId(id = "12"),
                companyId = ClSrvCompanyId(id = "12"),
                id = ClSrvOrderId(id = "12"),
                dateTime = Instant.parse("2023-06-03T10:15:30Z"),
                address = "Адрес"
            )
        )

        // when
        val createTransport = context.toTransport() as OrderReadResponse

        // then
        assertEquals(expected = context.requestId.asString(), actual = createTransport.requestId)
        assertEquals(expected = "read", actual = createTransport.responseType)
        assertEquals(expected = context.orderResponse.userId.asString(), actual = createTransport.order!!.userId)
        assertEquals(expected = context.orderResponse.companyId.asString(), actual = createTransport.order!!.companyId)
        assertEquals(expected = context.orderResponse.address, actual = createTransport.order!!.address)
        assertEquals(expected = context.orderResponse.dateTime.toString(), actual = createTransport.order!!.dateTime)
    }

    @Test
    fun toTransportSearchByCompanyId() {
        // given
        val context = ClSrvContext(
            command = ClSrvCommand.SEARCH_BY_COMPANY_ID,
            state = ClSrvState.RUNNING,
            errors = mutableListOf(),
            workMode = ClSrvWorkMode.PROD,
            stubCase = ClSrvStubs.SUCCESS,
            requestId = ClSrvRequestId(id = UUID.randomUUID().toString()),
            timeStart = Clock.System.now(),
            ordersResponse = mutableListOf(
                ClSrvOrder(
                    userId = ClSrvUserId(id = "12"),
                    companyId = ClSrvCompanyId(id = "12"),
                    id = ClSrvOrderId(id = "12"),
                    dateTime = Instant.parse("2023-06-03T10:15:30Z"),
                    address = "Адрес"
                )
            )
        )

        // when
        val createTransport = context.toTransport() as OrderSearchByCompanyIdResponse

        // then
        assertEquals(expected = context.requestId.asString(), actual = createTransport.requestId)
        assertEquals(expected = "search_by_company_id", actual = createTransport.responseType)
        assertEquals(expected = context.ordersResponse.first().userId.asString(), actual = createTransport.orders!!.first().userId)
        assertEquals(expected = context.ordersResponse.first().companyId.asString(), actual = createTransport.orders!!.first().companyId)
        assertEquals(expected = context.ordersResponse.first().address, actual = createTransport.orders!!.first().address)
        assertEquals(expected = context.ordersResponse.first().dateTime.toString(), actual = createTransport.orders!!.first().dateTime)
    }

    @Test
    fun toTransportSearchByUserId() {
        // given
        val context = ClSrvContext(
            command = ClSrvCommand.SEARCH_BY_USER_ID,
            state = ClSrvState.RUNNING,
            errors = mutableListOf(),
            workMode = ClSrvWorkMode.PROD,
            stubCase = ClSrvStubs.SUCCESS,
            requestId = ClSrvRequestId(id = UUID.randomUUID().toString()),
            timeStart = Clock.System.now(),
            ordersResponse = mutableListOf(
                ClSrvOrder(
                    userId = ClSrvUserId(id = "12"),
                    companyId = ClSrvCompanyId(id = "12"),
                    id = ClSrvOrderId(id = "12"),
                    dateTime = Instant.parse("2023-06-03T10:15:30Z"),
                    address = "Адрес"
                )
            )
        )

        // when
        val createTransport = context.toTransport() as OrderSearchByUserIdResponse

        // then
        assertEquals(expected = context.requestId.asString(), actual = createTransport.requestId)
        assertEquals(expected = "search_by_user_id", actual = createTransport.responseType)
        assertEquals(expected = context.ordersResponse.first().userId.asString(), actual = createTransport.orders!!.first().userId)
        assertEquals(expected = context.ordersResponse.first().companyId.asString(), actual = createTransport.orders!!.first().companyId)
        assertEquals(expected = context.ordersResponse.first().address, actual = createTransport.orders!!.first().address)
        assertEquals(expected = context.ordersResponse.first().dateTime.toString(), actual = createTransport.orders!!.first().dateTime)
    }
}