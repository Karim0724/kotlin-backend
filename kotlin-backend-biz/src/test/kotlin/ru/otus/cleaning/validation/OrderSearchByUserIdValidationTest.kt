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
class OrderSearchByUserIdValidationTest {
    private val ctx = ClSrvContext().apply {
        command = ClSrvCommand.SEARCH_BY_USER_ID
        workMode = ClSrvWorkMode.TEST
    }
    private val processor = ClSrvProcessor()

    @Test
    fun incorrectUserId() = runTest {
        // given
        val orderWithBadUserId = ClSrvOrder(
            userId = ClSrvUserId(id = ""),
            companyId = ClSrvCompanyId(id = "1"),
            id = ClSrvOrderId(id = "1"),
            dateTime = Clock.System.now(),
            address = "address"
        )
        val ctx = ctx.apply {
            orderRequest = orderWithBadUserId
        }

        // when
        processor.process(ctx)

        // then
        val error = ctx.errors.first()
        assertEquals(actual = error.field, expected = "userId")
        assertEquals(actual = error.code, expected = "badFormat")
    }
}