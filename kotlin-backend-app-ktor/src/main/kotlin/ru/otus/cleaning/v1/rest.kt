import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.otus.cleaning.ClSrvProcessor
import ru.otus.cleaning.v1.create
import ru.otus.cleaning.v1.readById
import ru.otus.cleaning.v1.searchByCompanyId
import ru.otus.cleaning.v1.searchByUserId

fun Route.v1Order(processor: ClSrvProcessor) {
    route("order") {
        post("create") {
            call.create(processor)
        }
        post("readById") {
            call.readById(processor)
        }
        post("searchByUserId") {
            call.searchByUserId(processor)
        }
        post("searchByCompanyId") {
            call.searchByCompanyId(processor)
        }
    }
}