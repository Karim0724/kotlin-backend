package ru.otus.cleaning.helpers

import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvError
import ru.otus.cleaning.models.ClSrvState

fun Throwable.asClSrvError(
    code: String = "unknown",
    group: String = "exceptions",
    message: String = this.message ?: "",
) = ClSrvError(
    code = code,
    group = group,
    field = "",
    message = message,
    exception = this,
)

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

val errorNotFound = ClSrvError(
    field = "id",
    message = "Not Found",
    code = "not-found"
)

val errorEmptyId = ClSrvError(
    field = "id",
    message = "Id must not be null or blank"
)