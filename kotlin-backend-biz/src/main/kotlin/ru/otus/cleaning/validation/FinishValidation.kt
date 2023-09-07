package ru.otus.cleaning.validation

import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvState
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker

fun ICorChainDsl<ClSrvContext>.finishOrderValidation(title: String) = worker {
    this.title = title
    on { state == ClSrvState.RUNNING }
    handle {
        orderValidated = orderValidating
    }
}
