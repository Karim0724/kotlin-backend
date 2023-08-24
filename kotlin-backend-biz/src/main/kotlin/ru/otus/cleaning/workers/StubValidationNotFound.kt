import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvError
import ru.otus.cleaning.models.ClSrvState
import ru.otus.cleaning.stubs.ClSrvStubs
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker


fun ICorChainDsl<ClSrvContext>.stubValidationNotFound(title: String) =
    worker {
        this.title = title
        on { stubCase == ClSrvStubs.NOT_FOUND && state == ClSrvState.RUNNING }
        handle {
            errors.add(
                ClSrvError(
                    code = ClSrvStubs.NOT_FOUND.name,
                    group = "validation",
                    field = "",
                    message = "Ресурс не найден",
                    exception = null,
                )
            )
            state = ClSrvState.FAILING
        }
    }
