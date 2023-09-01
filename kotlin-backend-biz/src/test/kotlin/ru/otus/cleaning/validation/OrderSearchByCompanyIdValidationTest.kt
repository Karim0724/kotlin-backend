package ru.otus.cleaning.validation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.ClSrvProcessor
import ru.otus.cleaning.models.*
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class OrderSearchByCompanyIdValidationTest {
    private val ctx = ClSrvContext().apply {
        command = ClSrvCommand.SEARCH_BY_COMPANY_ID
        workMode = ClSrvWorkMode.TEST
    }
    private val processor = ClSrvProcessor()

    @Test
    fun incorrectCompanyId() = runTest {
        // given
        val orderWithBadCompanyId = ClSrvOrder(
            userId = ClSrvUserId(id = "1"),
            companyId = ClSrvCompanyId(id = ""),
            id = ClSrvOrderId(id = "1"),
            dateTime = Clock.System.now(),
            address = "address"
        )
        val ctx = ctx.apply {
            orderRequest = orderWithBadCompanyId
        }

        // when
        processor.process(ctx)

        // then
        val error = ctx.errors.first()
        assertEquals(actual = error.field, expected = "companyId")
        assertEquals(actual = error.code, expected = "badFormat")
    }
}