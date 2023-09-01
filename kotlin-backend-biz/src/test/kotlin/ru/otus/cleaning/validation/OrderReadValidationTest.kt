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
class OrderReadValidationTest {
    private val ctx = ClSrvContext().apply {
        command = ClSrvCommand.READ
        workMode = ClSrvWorkMode.TEST
    }
    private val processor = ClSrvProcessor()

    @Test
    fun incorrectId() = runTest {
        // given
        val orderWithBadId = ClSrvOrder(
            userId = ClSrvUserId(id = "1"),
            companyId = ClSrvCompanyId(id = "1"),
            id = ClSrvOrderId(id = ""),
            dateTime = Clock.System.now(),
            address = "address"
        )
        val ctx = ctx.apply {
            orderRequest = orderWithBadId
        }

        // when
        processor.process(ctx)

        // then
        val error = ctx.errors.first()
        assertEquals(actual = error.field, expected = "id")
        assertEquals(actual = error.code, expected = "badFormat")
    }
}