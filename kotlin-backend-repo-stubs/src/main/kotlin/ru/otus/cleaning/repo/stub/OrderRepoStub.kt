package ru.otus.cleaning.repo.stub

import ru.otus.cleaning.ClSrvStub
import ru.otus.cleaning.repo.*

class OrderRepoStub : IOrderRepository {
    override suspend fun createOrder(dbOrderRequest: DbOrderRequest): DbOrderResponse {
        return DbOrderResponse(
            data = ClSrvStub.createOrderStub(dbOrderRequest.order),
            isSuccess = true,
            errors = emptyList()
        )
    }

    override suspend fun readOrder(dbOrderIdRequest: DbOrderIdRequest): DbOrderResponse {
        return DbOrderResponse(
            data = ClSrvStub.get(),
            isSuccess = true,
            errors = emptyList()
        )
    }

    override suspend fun searchByUserId(dbUserIdRequest: DbUserIdRequest): DbOrdersResponse {
        return DbOrdersResponse(
            data = listOf(ClSrvStub.get()),
            isSuccess = true,
            errors = emptyList()
        )
    }

    override suspend fun searchByCompanyId(dbCompanyIdRequest: DbCompanyIdRequest): DbOrdersResponse {
        return DbOrdersResponse(
            data = listOf(ClSrvStub.get()),
            isSuccess = true,
            errors = emptyList()
        )
    }
}