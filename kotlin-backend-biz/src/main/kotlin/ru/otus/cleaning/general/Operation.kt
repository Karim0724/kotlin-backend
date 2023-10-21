import ru.otus.cleaning.ClSrvContext
import ru.otus.cleaning.models.ClSrvCommand
import ru.otus.cleaning.models.ClSrvState
import ru.otus.otuskotlin.marketplace.cor.ICorChainDsl
import ru.otus.otuskotlin.marketplace.cor.chain

fun ICorChainDsl<ClSrvContext>.operation(
    title: String,
    command: ClSrvCommand,
    block: ICorChainDsl<ClSrvContext>.() -> Unit
) {
    chain {
        this.title = title
        on { this.command == command && this.state == ClSrvState.RUNNING}
        block()
    }
}