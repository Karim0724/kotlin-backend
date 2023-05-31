import kotlin.test.Test

class StubTest {
    var x = 5

    @Test
    fun firstTest() {
        x = 2
    }

    @Test
    fun secondTest() {
        println(x)
    }
}