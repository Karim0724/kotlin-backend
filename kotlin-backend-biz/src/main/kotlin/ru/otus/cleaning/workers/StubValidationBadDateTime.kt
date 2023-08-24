import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvError
import ru.otus.cleaning.models.ClSrvState
import ru.otus.cleaning.stubs.ClSrvStubs
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker

fun ICorChainDsl<ClSrvContext>.stubValidationBadDateTime(title: String) =
    worker {
        this.title = title
        on { stubCase == ClSrvStubs.BAD_DATE_TIME && state == ClSrvState.RUNNING }
        handle {
            errors.add(
                ClSrvError(
                    code = ClSrvStubs.BAD_DATE_TIME.name,
                    group = "validation",
                    field = "datetime",
                    message = "Неправильно указана дата",
                    exception = null,
                )
            )
            state = ClSrvState.FAILING
        }
    }