package ru.otus.cleaning.validation

import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.helpers.fail
import ru.otus.cleaning.models.ClSrvError
import ru.otus.cleaning.models.ClSrvOrderId
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker

fun ICorChainDsl<ClSrvContext>.validateIdFormat(title: String) = worker {
    this.title = title
    on {
        val id = this.orderValidating.id.asString().toLongOrNull()
        this.orderValidating.id == ClSrvOrderId.NONE || id == null || id <= 0
    }
    handle {
        fail(
            ClSrvError(
                code = "badFormat",
                group = "validation",
                field = "id",
                message = "Иденификатор заявки должен быть целочисленым"
            )
        )
    }
}