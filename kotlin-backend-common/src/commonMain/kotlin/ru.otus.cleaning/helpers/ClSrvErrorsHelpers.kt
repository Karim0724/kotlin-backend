import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvError
import ru.otus.cleaning.models.ClSrvState

fun ClSrvContext.fail(error: ClSrvError) {
    addError(error)
    this.state = ClSrvState.FAILING
}

fun ClSrvContext.addError(error: ClSrvError) = this.errors.add(error)

fun errorAdministration(
    /**
     * Код, характеризующий ошибку. Не должен включать имя поля или указание на валидацию.
     * Например: empty, badSymbols, tooLong, etc
     */
    field: String = "",
    violationCode: String,
    description: String,
    exception: Exception? = null,
) = ClSrvError(
    field = field,
    code = "administration-$violationCode",
    group = "administration",
    message = "Microservice management error: $description",
    exception = exception,
)