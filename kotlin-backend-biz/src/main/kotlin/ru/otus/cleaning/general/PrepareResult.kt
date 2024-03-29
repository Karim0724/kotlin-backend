package ru.otus.cleaning.general

import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvState
import ru.otus.cleaning.models.ClSrvWorkMode
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker

fun ICorChainDsl<ClSrvContext>.prepareResult(title: String) = worker {
    this.title = title
    description = "Подготовка данных для ответа клиенту на запрос"
    on { workMode != ClSrvWorkMode.STUB }
    handle {
        orderResponse = orderRepoDone
        ordersResponse = ordersRepoDone
        state = when (val st = state) {
            ClSrvState.RUNNING -> ClSrvState.FINISHING
            else -> st
        }
    }
}
