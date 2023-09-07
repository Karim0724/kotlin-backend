package ru.otus.cleaning.stub

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import org.junit.Test
import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.ClSrvProcessor
import ru.otus.cleaning.models.*
import ru.otus.cleaning.stubs.ClSrvStubs
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class OrderCreateStubTest {
    private val processor = ClSrvProcessor()

    private val companyId = "1"
    private val userId = "1"
    private val address = "Ул шверника д 1 к 1"
    private val datetime = Clock.System.now()

    @Test
    fun success() = runTest {
        // given
        val context = ClSrvContext(
            command = ClSrvCommand.CREATE,
            workMode = ClSrvWorkMode.STUB,
            stubCase = ClSrvStubs.SUCCESS,
            requestId = ClSrvRequestId(id = "1"),
            orderRequest = ClSrvOrder(
                userId = ClSrvUserId(id = userId),
                companyId = ClSrvCompanyId(id = companyId),
                dateTime = datetime,
                address = address
            )
        )

        // when
        processor.process(context)

        // then
        with(context) {
            assertEquals(expected = datetime, actual = orderResponse.dateTime)
            assertEquals(expected = companyId, actual = orderResponse.companyId.asString())
            assertEquals(expected = userId, actual = orderResponse.userId.asString())
            assertEquals(expected = address, actual = orderResponse.address)
            assertTrue { orderResponse.id.asString().isNotEmpty() }
        }
    }

    @Test
    fun badCompanyId() = runTest {
        // given
        val context = ClSrvContext(
            command = ClSrvCommand.CREATE,
            workMode = ClSrvWorkMode.STUB,
            stubCase = ClSrvStubs.BAD_COMPANY_ID,
            requestId = ClSrvRequestId(id = "1"),
            orderRequest = ClSrvOrder(
                userId = ClSrvUserId(id = userId),
                companyId = ClSrvCompanyId(id = companyId),
                dateTime = datetime,
                address = address
            )
        )

        // when
        processor.process(context)

        // then
        with(context) {
            assertEquals(expected = ClSrvState.FAILING, actual = state)
            assertEquals(expected = ClSrvStubs.BAD_COMPANY_ID.name, actual = errors.first().code)
            assertEquals(expected = "companyId", actual = errors.first().field)
        }
    }

    @Test
    fun badUserId() = runTest {
        // given
        val context = ClSrvContext(
            command = ClSrvCommand.CREATE,
            workMode = ClSrvWorkMode.STUB,
            stubCase = ClSrvStubs.BAD_USER_ID,
            requestId = ClSrvRequestId(id = "1"),
            orderRequest = ClSrvOrder(
                userId = ClSrvUserId(id = userId),
                companyId = ClSrvCompanyId(id = companyId),
                dateTime = datetime,
                address = address
            )
        )

        // when
        processor.process(context)

        // then
        with(context) {
            assertEquals(expected = ClSrvState.FAILING, actual = state)
            assertEquals(expected = ClSrvStubs.BAD_USER_ID.name, actual = errors.first().code)
            assertEquals(expected = "userId", actual = errors.first().field)
        }
    }

    @Test
    fun badDatetime() = runTest {
        // given
        val context = ClSrvContext(
            command = ClSrvCommand.CREATE,
            workMode = ClSrvWorkMode.STUB,
            stubCase = ClSrvStubs.BAD_DATE_TIME,
            requestId = ClSrvRequestId(id = "1"),
            orderRequest = ClSrvOrder(
                userId = ClSrvUserId(id = userId),
                companyId = ClSrvCompanyId(id = companyId),
                dateTime = datetime,
                address = address
            )
        )

        // when
        processor.process(context)

        // then
        with(context) {
            assertEquals(expected = ClSrvState.FAILING, actual = state)
            assertEquals(expected = ClSrvStubs.BAD_DATE_TIME.name, actual = errors.first().code)
            assertEquals(expected = "datetime", actual = errors.first().field)
        }
    }
}