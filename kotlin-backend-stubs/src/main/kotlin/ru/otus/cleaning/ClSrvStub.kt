package ru.otus.cleaning

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import ru.otus.cleaning.models.*
import java.util.*

object ClSrvStub {
    fun get() = ClSrvOrder(
        userId = ClSrvUserId(id = "1"),
        companyId = ClSrvCompanyId(id = "1"),
        id = ClSrvOrderId(id = "1"),
        dateTime = Clock.System.now(),
        address = "Ул Шверника 1к1 кв 235",
        lock = ClSrvOrderLock(id = UUID.randomUUID().toString())

    )

    fun createOrderStub(clSrvRequest: ClSrvOrder): ClSrvOrder {
        return ClSrvOrder(
            userId = ClSrvUserId(id = clSrvRequest.userId.asString()),
            companyId = ClSrvCompanyId(id = clSrvRequest.companyId.asString()),
            id = ClSrvOrderId(id = "1"),
            dateTime = clSrvRequest.dateTime,
            address = clSrvRequest.address
        )
    }

    fun readOrderStub(clSrvRequest: ClSrvOrder): ClSrvOrder {
        return ClSrvOrder(
            userId = ClSrvUserId(id = "1"),
            companyId = ClSrvCompanyId(id = "1"),
            id = ClSrvOrderId(id = clSrvRequest.id.asString()),
            dateTime = Instant.parse("2011-01-03T10:15:30Z"),
            address = "Ул Шверника 1к1 кв 235"
        )
    }

    fun searchByUserIdStub(clSrvRequest: ClSrvOrder): List<ClSrvOrder>{
        return ClSrvOrder(
            userId = ClSrvUserId(id = clSrvRequest.userId.asString()),
            companyId = ClSrvCompanyId(id = "1"),
            id = ClSrvOrderId(id = "1"),
            dateTime = Instant.parse("2011-01-03T10:15:30Z"),
            address = "Ул Шверника 1к1 кв 235"
        ).let { listOf(it) }
    }

    fun searchByCompanyIdStub(clSrvRequest: ClSrvOrder): List<ClSrvOrder> {
        return ClSrvOrder(
            userId = ClSrvUserId(id = "1"),
            companyId = ClSrvCompanyId(id = clSrvRequest.companyId.asString()),
            id = ClSrvOrderId(id = "1"),
            dateTime = Instant.parse("2011-01-03T10:15:30Z"),
            address = "Ул Шверника 1к1 кв 235"
        ).let { listOf(it) }
    }
}