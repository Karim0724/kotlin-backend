import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvError
import ru.otus.cleaning.models.ClSrvState
import ru.otus.cleaning.stubs.ClSrvStubs
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker


fun ICorChainDsl<ClSrvContext>.stubValidationBadCompanyId(title: String) =
    worker {
        this.title = title
        on { stubCase == ClSrvStubs.BAD_COMPANY_ID && state == ClSrvState.RUNNING }
        handle {
            errors.add(
                ClSrvError(
                    code = ClSrvStubs.BAD_COMPANY_ID.name,
                    group = "validation",
                    field = "companyId",
                    message = "Не правильно указан идентификатор компании",
                    exception = null,
                )
            )
            state = ClSrvState.FAILING
        }
    }
