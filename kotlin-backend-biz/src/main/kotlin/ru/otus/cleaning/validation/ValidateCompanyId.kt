package ru.otus.cleaning.validation

import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.helpers.fail
import ru.otus.cleaning.models.ClSrvCompanyId
import ru.otus.cleaning.models.ClSrvError
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker

fun ICorChainDsl<ClSrvContext>.validateCompanyIdFormat(title: String) = worker {
    this.title = title
    on {
        val id = this.orderValidating.companyId.asString().toLongOrNull()
        this.orderValidating.companyId == ClSrvCompanyId.NONE || id == null || id <= 0
    }
    handle {
        fail(
            ClSrvError(
                code = "badFormat",
                group = "validation",
                field = "companyId",
                message = "Иденификатор компании должен быть целочисленым"
            )
        )
    }
}