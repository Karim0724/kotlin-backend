package ru.otus.cleaning.validation

import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvState
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.chain

fun ICorChainDsl<ClSrvContext>.validation(
    title: String,
    block: ICorChainDsl<ClSrvContext>.() -> Unit
) = chain {
    this.title = title
    on { this.state == ClSrvState.RUNNING}
    block()
}