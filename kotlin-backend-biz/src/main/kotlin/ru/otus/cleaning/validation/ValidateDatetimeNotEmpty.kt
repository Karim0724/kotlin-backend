package ru.otus.cleaning.validation

import kotlinx.datetime.Instant
import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.NONE
import ru.otus.cleaning.helpers.fail
import ru.otus.cleaning.models.ClSrvError
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker

fun ICorChainDsl<ClSrvContext>.validateDatetimeNotEmpty(title: String) = worker {
    this.title = title
    on {
        this.orderValidating.dateTime == Instant.NONE
    }
    handle {
        fail(
            ClSrvError(
                code = "badFormat",
                group = "validation",
                field = "datetime",
                message = "Дата заказа должна быть указана"
            )
        )
    }
}