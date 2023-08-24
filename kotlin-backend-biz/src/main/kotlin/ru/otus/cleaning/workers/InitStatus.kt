package ru.otus.cleaning.workers

import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvState
import ru.otus.cleaning.stubs.ClSrvStubs
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker

fun ICorChainDsl<ClSrvContext>.initStatus(title: String) =
    worker {
        this.title = title
        on { state == ClSrvState.NONE }
        handle { state = ClSrvState.RUNNING }
    }