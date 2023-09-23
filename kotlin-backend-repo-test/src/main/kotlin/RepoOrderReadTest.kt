import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.datetime.Clock
import ru.otus.cleaning.models.*
import ru.otus.cleaning.repo.DbOrderIdRequest
import ru.otus.cleaning.repo.IOrderRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
abstract class RepoOrderReadTest {
    abstract val orderRepository: IOrderRepository

    @Test
    fun readSuccess() = runRepoTest {
        val readOrder = initObjects[0]
        val result = orderRepository.readOrder(DbOrderIdRequest(readOrder.id))

        assertTrue { result.isSuccess }
        assertEquals(expected = readOrder, actual = result.data)
        assertEquals(expected = emptyList(), actual = result.errors)
    }

    @Test
    fun readNotFound() = runRepoTest {
        val result = orderRepository.readOrder(DbOrderIdRequest(notFoundOrder.id))

        assertFalse { result.isSuccess }
        assertEquals(expected = result.errors.first().code, actual = "not-found")
    }

    companion object : IInitObjects<ClSrvOrder> {
        override val initObjects: List<ClSrvOrder> = listOf(
            ClSrvOrder(
                userId = ClSrvUserId(id = "1"),
                companyId = ClSrvCompanyId(id = "1"),
                id = ClSrvOrderId(id = "1"),
                dateTime = Clock.System.now(),
                address = "ул Шверника 1к1",
                lock = ClSrvOrderLock(id = "1")
            )
        )

        val notFoundOrder = ClSrvOrder(
            userId = ClSrvUserId(id = "2"),
            companyId = ClSrvCompanyId(id = "2"),
            id = ClSrvOrderId(id = "2"),
            dateTime = Clock.System.now(),
            address = "",
            lock = ClSrvOrderLock(id = "2")
        )
    }
}