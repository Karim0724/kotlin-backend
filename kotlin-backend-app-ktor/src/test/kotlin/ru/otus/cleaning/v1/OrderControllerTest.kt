package ru.otus.cleaning.v1

import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.testing.*
import ru.otus.cleaning.api.v1.models.OrderCreateObject
import ru.otus.cleaning.api.v1.models.OrderCreateRequest
import ru.otus.cleaning.api.v1.models.OrderCreateResponse
import ru.otus.cleaning.api.v1.models.OrderDebug
import ru.otus.cleaning.api.v1.models.OrderReadObject
import ru.otus.cleaning.api.v1.models.OrderReadRequest
import ru.otus.cleaning.api.v1.models.OrderReadResponse
import ru.otus.cleaning.api.v1.models.OrderRequestDebugMode
import ru.otus.cleaning.api.v1.models.OrderRequestDebugStubs
import ru.otus.cleaning.api.v1.models.OrderSearchByCompanyIdObject
import ru.otus.cleaning.api.v1.models.OrderSearchByCompanyIdRequest
import ru.otus.cleaning.api.v1.models.OrderSearchByCompanyIdResponse
import ru.otus.cleaning.api.v1.models.OrderSearchByUserIdObject
import ru.otus.cleaning.api.v1.models.OrderSearchByUserIdRequest
import ru.otus.cleaning.api.v1.models.OrderSearchByUserIdResponse
import ru.otus.cleaning.apiV1Mapper
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class OrderControllerTest {
    @Test
    fun create() = testApplication {
        val client = myClient()
        val createRequest = OrderCreateRequest(
            requestType = "create",
            requestId = UUID.randomUUID().toString(),
            debug = OrderDebug(mode = OrderRequestDebugMode.STUB, stub = OrderRequestDebugStubs.SUCCESS),
            order = OrderCreateObject(userId = USER_ID, companyId = COMPANY_ID, address = ADDRESS, dateTime = ORDER_DATE_TIME)
        )

        val response = client.post("/v1/order/create") {
            contentType(ContentType.Application.Json)
            setBody(createRequest)
        }
        val responseBody = response.body<OrderCreateResponse>()

        assertEquals(200, response.status.value)
        assertNotNull(responseBody.order!!.id)
        assertEquals(expected = USER_ID, actual = responseBody.order!!.userId)
        assertEquals(expected = COMPANY_ID, actual = responseBody.order!!.companyId)
        assertEquals(expected = ORDER_DATE_TIME, actual = responseBody.order!!.dateTime)
        assertEquals(expected = ADDRESS, actual = responseBody.order!!.address)
    }

    @Test
    fun read() = testApplication {
        val client = myClient()
        val createRequest = OrderReadRequest(
            requestType = "read",
            requestId = UUID.randomUUID().toString(),
            debug = OrderDebug(mode = OrderRequestDebugMode.STUB, stub = OrderRequestDebugStubs.SUCCESS),
            order = OrderReadObject(id = "1")
        )

        val response = client.post("/v1/order/readById") {
            contentType(ContentType.Application.Json)
            setBody(createRequest)
        }
        val responseBody = response.body<OrderReadResponse>()

        assertEquals(200, response.status.value)
        assertNotNull(responseBody.order!!.id)
        assertEquals(expected = USER_ID, actual = responseBody.order!!.userId)
        assertEquals(expected = COMPANY_ID, actual = responseBody.order!!.companyId)
        assertEquals(expected = ORDER_DATE_TIME, actual = responseBody.order!!.dateTime)
        assertEquals(expected = ADDRESS, actual = responseBody.order!!.address)
    }

    @Test
    fun searchByCompanyId() = testApplication {
        val client = myClient()
        val createRequest = OrderSearchByCompanyIdRequest(
            requestType = "searchByCompanyId",
            requestId = UUID.randomUUID().toString(),
            debug = OrderDebug(mode = OrderRequestDebugMode.STUB, stub = OrderRequestDebugStubs.SUCCESS),
            order = OrderSearchByCompanyIdObject(companyId = "1")
        )

        val response = client.post("/v1/order/searchByCompanyId") {
            contentType(ContentType.Application.Json)
            setBody(createRequest)
        }
        val responseBody = response.body<OrderSearchByCompanyIdResponse>()

        assertEquals(200, response.status.value)
        assertNotNull(responseBody.orders!!.first().id)
        assertEquals(expected = USER_ID, actual = responseBody.orders!!.first().userId)
        assertEquals(expected = COMPANY_ID, actual = responseBody.orders!!.first().companyId)
        assertEquals(expected = ORDER_DATE_TIME, actual = responseBody.orders!!.first().dateTime)
        assertEquals(expected = ADDRESS, actual = responseBody.orders!!.first().address)
    }

    @Test
    fun searchByUserId() = testApplication {
        val client = myClient()
        val createRequest = OrderSearchByUserIdRequest(
            requestType = "searchByUserId",
            requestId = UUID.randomUUID().toString(),
            debug = OrderDebug(mode = OrderRequestDebugMode.STUB, stub = OrderRequestDebugStubs.SUCCESS),
            order = OrderSearchByUserIdObject(userId = "1")
        )

        val response = client.post("/v1/order/searchByUserId") {
            contentType(ContentType.Application.Json)
            setBody(createRequest)
        }
        val responseBody = response.body<OrderSearchByUserIdResponse>()

        assertEquals(200, response.status.value)
        assertNotNull(responseBody.orders!!.first().id)
        assertEquals(expected = USER_ID, actual = responseBody.orders!!.first().userId)
        assertEquals(expected = COMPANY_ID, actual = responseBody.orders!!.first().companyId)
        assertEquals(expected = ORDER_DATE_TIME, actual = responseBody.orders!!.first().dateTime)
        assertEquals(expected = ADDRESS, actual = responseBody.orders!!.first().address)
    }

    private fun ApplicationTestBuilder.myClient() = createClient {
        install(ContentNegotiation) {
            jackson {
                setConfig(apiV1Mapper.serializationConfig)
                setConfig(apiV1Mapper.deserializationConfig)
            }
        }
    }

    companion object {
        const val ADDRESS = "Ул Шверника 1к1 кв 235"
        const val ORDER_DATE_TIME = "2011-01-03T10:15:30Z"
        const val USER_ID = "1"
        const val COMPANY_ID = "1"
    }
}