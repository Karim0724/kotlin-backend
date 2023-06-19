package ru.otus.cleaning

import org.junit.Test
import ru.otus.cleaning.api.v1.models.IResponse
import ru.otus.cleaning.api.v1.models.OrderCreateResponse
import ru.otus.cleaning.api.v1.models.OrderResponseObject
import ru.otus.cleaning.api.v1.models.ResponseResult
import java.math.BigDecimal
import java.util.*
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestSerializationTest {
    private val orderCreateResponse = OrderCreateResponse(
        requestId = UUID.randomUUID().toString(),
        order = OrderResponseObject(
            userId = BigDecimal(1),
            companyId = BigDecimal(1),
            address = "Москва, ул Шверника 1к2",
            dateTime = "2011-12-03T10:15:30+01:00"
        ),
        responseType = "create",
        result = ResponseResult.SUCCESS,
        errors = listOf()
    )

    @Test
    fun serialize() {
        // when
        val json = apiV1Mapper.writeValueAsString(orderCreateResponse)

        // then
        assertContains(json, Regex("\"userId\":\\s*${orderCreateResponse.order?.userId}"))
        assertContains(json, Regex("\"companyId\":\\s*${orderCreateResponse.order?.companyId}"))
        assertContains(json, Regex("\"address\":\\s*\"${orderCreateResponse.order?.address}\""))
        assertContains(json, Regex("\"responseType\":\\s*\"${orderCreateResponse.responseType}\""))
        assertContains(json, Regex("\"dateTime\":\\s*\"2011-12-03T10:15:30\\+01:00\""))
        assertContains(json, Regex("\"result\":\\s*\"${ResponseResult.SUCCESS}\""))
    }

    @Test
    fun deserialize() {
        // when
        val json = apiV1Mapper.writeValueAsString(orderCreateResponse)
        val obj = apiV1Mapper.readValue(json, IResponse::class.java) as OrderCreateResponse

        // then
        assertEquals(orderCreateResponse, obj)
    }
}