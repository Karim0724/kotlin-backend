package ru.otus.cleaning.validation

import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.helpers.fail
import ru.otus.cleaning.models.ClSrvError
import ru.otus.cleaning.models.ClSrvUserId
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker

fun ICorChainDsl<ClSrvContext>.validateUserIdFormat(title: String) = worker {
    this.title = title
    on {
        val id = this.orderValidating.userId.asString().toLongOrNull()
        this.orderValidating.userId == ClSrvUserId.NONE || id == null || id <= 0
    }
    handle {
        fail(
            ClSrvError(
                code = "badFormat",
                group = "validation",
                field = "userId",
                message = "Иденификатор клиента должен быть целочисленым"
            )
        )
    }
}