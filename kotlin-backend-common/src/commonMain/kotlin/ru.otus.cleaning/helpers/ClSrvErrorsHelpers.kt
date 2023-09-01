import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvError
import ru.otus.cleaning.models.ClSrvState

fun ClSrvContext.fail(error: ClSrvError) {
    addError(error)
    this.state = ClSrvState.FAILING
}

fun ClSrvContext.addError(error: ClSrvError) = this.errors.add(error)