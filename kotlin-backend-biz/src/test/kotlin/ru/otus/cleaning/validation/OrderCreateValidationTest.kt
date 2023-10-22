package ru.otus.cleaning.validation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.ClSrvCorSettings
import ru.otus.cleaning.ClSrvProcessor
import ru.otus.cleaning.NONE
import ru.otus.cleaning.models.*
import ru.otus.cleaning.repo.IOrderRepository
import ru.otus.cleaning.repo.stub.OrderRepoStub
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class OrderCreateValidationTest {
    private val ctx = ClSrvContext().apply {
        command = ClSrvCommand.CREATE
        workMode = ClSrvWorkMode.TEST
    }
    private val processor = ClSrvProcessor(ClSrvCorSettings(repoTest = OrderRepoStub()))

    @Test
    fun incorrectAddress() = runTest {
        // given
        val orderWithBadAddress = ClSrvOrder(
            userId = ClSrvUserId(id = "1"),
            companyId = ClSrvCompanyId(id = "1"),
            id = ClSrvOrderId(id = "1"),
            dateTime = Clock.System.now(),
            address = ""
        )
        val ctx = ctx.apply {
            orderRequest = orderWithBadAddress
        }

        // when
        processor.process(ctx)

        // then
        val error = ctx.errors.first()
        assertEquals(actual = error.field, expected = "address")
        assertEquals(actual = error.code, expected = "badFormat")
    }

    @Test
    fun incorrectCompanyId() = runTest {
        // given
        val orderWithBadCompanyId = ClSrvOrder(
            userId = ClSrvUserId(id = "1"),
            companyId = ClSrvCompanyId(id = ""),
            id = ClSrvOrderId(id = "1"),
            dateTime = Clock.System.now(),
            address = "ул Швеника"
        )
        val ctx = ctx.apply {
            orderRequest = orderWithBadCompanyId
        }

        // when
        processor.process(ctx)

        // then
        val error = ctx.errors.first()
        assertTrue { ctx.state == ClSrvState.FAILING }
        assertEquals(actual = error.field, expected = "companyId")
        assertEquals(actual = error.code, expected = "badFormat")
    }

    @Test
    fun incorrectUserId() = runTest {
        // given
        val orderWithBadUserId = ClSrvOrder(
            userId = ClSrvUserId(id = ""),
            companyId = ClSrvCompanyId(id = "1"),
            id = ClSrvOrderId(id = "1"),
            dateTime = Clock.System.now(),
            address = "ул Швеника"
        )
        val ctx = ctx.apply {
            orderRequest = orderWithBadUserId
        }

        // when
        processor.process(ctx)

        // then
        val error = ctx.errors.first()
        assertTrue { ctx.state == ClSrvState.FAILING }
        assertEquals(actual = error.field, expected = "userId")
        assertEquals(actual = error.code, expected = "badFormat")
    }

    @Test
    fun incorrectDatetime() = runTest {
        // given
        val orderWithBadDatetime = ClSrvOrder(
            userId = ClSrvUserId(id = "1"),
            companyId = ClSrvCompanyId(id = "1"),
            id = ClSrvOrderId(id = "1"),
            dateTime = Instant.NONE,
            address = "ул Швеника"
        )
        val ctx = ctx.apply {
            orderRequest = orderWithBadDatetime
        }

        // when
        processor.process(ctx)

        // then
        val error = ctx.errors.first()
        assertTrue { ctx.state == ClSrvState.FAILING }
        assertEquals(actual = error.field, expected = "datetime")
        assertEquals(actual = error.code, expected = "badFormat")
    }
}