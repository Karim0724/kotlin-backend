package ru.otus.cleaning.repo

import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvState
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker

fun ICorChainDsl<ClSrvContext>.repoPrepare(title: String) = worker {
    this.title = title
    description = "Подготовка заказа к сохранению в БД"
    on { state == ClSrvState.RUNNING }
    handle {
        orderRepoRead = orderValidated
        orderRepoPrepare = orderRepoRead
    }
}
