import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvError
import ru.otus.cleaning.models.ClSrvState
import ru.otus.cleaning.stubs.ClSrvStubs
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker


fun ICorChainDsl<ClSrvContext>.stubValidationBadUserId(title: String) =
    worker {
        this.title = title
        on { stubCase == ClSrvStubs.BAD_USER_ID && state == ClSrvState.RUNNING }
        handle {
            errors.add(
                ClSrvError(
                    code = ClSrvStubs.BAD_USER_ID.name,
                    group = "validation",
                    field = "userId",
                    message = "Неправильно указан идентификатор пользователя",
                    exception = null,
                )
            )
            state = ClSrvState.FAILING
        }
    }
