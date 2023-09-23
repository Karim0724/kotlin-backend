import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.datetime.Clock
import ru.otus.cleaning.models.*
import ru.otus.cleaning.repo.DbOrderIdRequest
import ru.otus.cleaning.repo.DbOrderRequest
import ru.otus.cleaning.repo.DbUserIdRequest
import ru.otus.cleaning.repo.IOrderRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
abstract class RepoOrderSearchByUserIdTest {
    abstract val orderRepository: IOrderRepository

    @Test
    fun searchSuccess() = runRepoTest {
        val result = orderRepository.searchByUserId(DbUserIdRequest(ClSrvUserId(userId)))

        assertTrue { result.isSuccess }
        assertEquals(expected = 2, actual = result.data!!.size)
        assertEquals(expected = emptyList(), actual = result.errors)
    }

    @Test
    fun searchEmpty() = runRepoTest {
        val result = orderRepository.searchByUserId(DbUserIdRequest(ClSrvUserId("200")))

        assertTrue { result.isSuccess }
        assertEquals(expected = 0, actual = result.data!!.size)
        assertEquals(expected = emptyList(), actual = result.errors)
    }

    companion object : IInitObjects<ClSrvOrder> {
        private const val userId = "12"
        override val initObjects: List<ClSrvOrder> = listOf(
            ClSrvOrder(
                userId = ClSrvUserId(id = userId),
                companyId = ClSrvCompanyId(id = "1"),
                id = ClSrvOrderId(id = "1"),
                dateTime = Clock.System.now(),
                address = "ул Шверника 1к1",
                lock = ClSrvOrderLock(id = "1")
            ),
            ClSrvOrder(
                userId = ClSrvUserId(id = userId),
                companyId = ClSrvCompanyId(id = "1"),
                id = ClSrvOrderId(id = "2"),
                dateTime = Clock.System.now(),
                address = "ул Шверника 1к1",
                lock = ClSrvOrderLock(id = "1")
            ),
            ClSrvOrder(
                userId = ClSrvUserId(id = "1"),
                companyId = ClSrvCompanyId(id = "1"),
                id = ClSrvOrderId(id = "3"),
                dateTime = Clock.System.now(),
                address = "ул Шверника 1к1",
                lock = ClSrvOrderLock(id = "1")
            )
        )

    }
}