package ru.otus.cleaning.workers

import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvError
import ru.otus.cleaning.models.ClSrvState
import ru.otus.cleaning.stubs.ClSrvStubs
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker

fun ICorChainDsl<ClSrvContext>.stubNoCase(title: String) =
    worker {
        this.title = title
        on { state == ClSrvState.RUNNING }
        handle {
            errors.add(
                ClSrvError(
                    code = "validation",
                    group = "validation",
                    field = "stub",
                    message = "Отсутствует stub case",
                    exception = null,
                )
            )
            state = ClSrvState.FAILING
        }
    }