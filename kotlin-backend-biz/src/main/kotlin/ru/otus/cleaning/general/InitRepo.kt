package ru.otus.cleaning.general

import ru.otus.cleaning.helpers.errorAdministration
import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.helpers.fail
import ru.otus.cleaning.models.ClSrvWorkMode
import ru.otus.cleaning.repo.IOrderRepository
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker

fun ICorChainDsl<ClSrvContext>.initRepo(title: String) = worker {
    this.title = title
    description = """
        Вычисление основного рабочего репозитория в зависимости от зпрошенного режима работы        
    """.trimIndent()
    handle {
        orderRepo = when (workMode) {
            ClSrvWorkMode.TEST -> settings.repoTest
            ClSrvWorkMode.STUB -> settings.repoStub
            else -> settings.repoProd
        }
        if (workMode != ClSrvWorkMode.STUB && orderRepo == IOrderRepository.NONE) fail(
            errorAdministration(
                field = "repo",
                violationCode = "dbNotConfigured",
                description = "The database is unconfigured for chosen workmode ($workMode). " +
                        "Please, contact the administrator staff"
            )
        )
    }
}
