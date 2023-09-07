import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvError
import ru.otus.cleaning.models.ClSrvOrderId
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.worker

fun ICorChainDsl<ClSrvContext>.validateAddressNotEmpty(title: String) = worker {
    this.title = title
    on { this.orderValidating.address.isEmpty() }
    handle {
        fail(
            ClSrvError(
                code = "badFormat",
                group = "validation",
                field = "address",
                message = "Аддрес не должен быть пустым"
            )
        )
    }
}