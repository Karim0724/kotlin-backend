package ru.otus.cleaning.workers

import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.ClSrvStub
import ru.otus.cleaning.models.*
import ru.otus.cleaning.stubs.ClSrvStubs
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker

fun ICorChainDsl<ClSrvContext>.stubCreateSuccess(title: String) =
    worker {
        this.title = title
        on { stubCase == ClSrvStubs.SUCCESS && state == ClSrvState.RUNNING }
        handle {
            orderResponse = ClSrvStub.createOrderStub(orderRequest)
            state = ClSrvState.FINISHING
        }
    }