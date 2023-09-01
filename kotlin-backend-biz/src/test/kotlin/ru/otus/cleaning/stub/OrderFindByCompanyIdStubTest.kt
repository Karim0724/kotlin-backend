package ru.otus.cleaning.stub

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.ClSrvProcessor
import ru.otus.cleaning.models.*
import ru.otus.cleaning.stubs.ClSrvStubs
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class OrderFindByCompanyIdStubTest {
    private val processor = ClSrvProcessor()
    private val companyId = "1"

    @Test
    fun success() = runTest {
        // given
        val context = ClSrvContext(
            command = ClSrvCommand.SEARCH_BY_COMPANY_ID,
            workMode = ClSrvWorkMode.STUB,
            stubCase = ClSrvStubs.SUCCESS,
            requestId = ClSrvRequestId(id = "1"),
            orderRequest = ClSrvOrder(
                companyId = ClSrvCompanyId(companyId)
            )
        )

        // when
        processor.process(context)

        // then
        with(context) {
            assertEquals(expected = companyId, actual = ordersResponse.first().companyId.asString())
        }
    }

    @Test
    fun notFound() = runTest {
        // given
        val context = ClSrvContext(
            command = ClSrvCommand.SEARCH_BY_COMPANY_ID,
            workMode = ClSrvWorkMode.STUB,
            stubCase = ClSrvStubs.NOT_FOUND,
            requestId = ClSrvRequestId(id = "1"),
            orderRequest = ClSrvOrder(
                companyId = ClSrvCompanyId(companyId)
            )
        )

        // when
        processor.process(context)

        // then
        with(context) {
            assertEquals(expected = ClSrvStubs.NOT_FOUND.name, actual = errors.first().code)
        }
    }

    @Test
    fun badCompanyId() = runTest {
        // given
        val context = ClSrvContext(
            command = ClSrvCommand.SEARCH_BY_COMPANY_ID,
            workMode = ClSrvWorkMode.STUB,
            stubCase = ClSrvStubs.BAD_COMPANY_ID,
            requestId = ClSrvRequestId(id = "1"),
            orderRequest = ClSrvOrder(
                companyId = ClSrvCompanyId(companyId)
            )
        )

        // when
        processor.process(context)

        // then
        with(context) {
            assertEquals(expected = ClSrvStubs.BAD_COMPANY_ID.name, actual = errors.first().code)
        }
    }
}