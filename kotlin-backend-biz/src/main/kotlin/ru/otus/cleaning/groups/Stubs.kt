import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvWorkMode
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.chain

fun ICorChainDsl<ClSrvContext>.stubs(
    title: String,
    block: ICorChainDsl<ClSrvContext>.() -> Unit
) {
    chain {
        this.title = title
        on { this.workMode == ClSrvWorkMode.STUB }
        block()
    }
}