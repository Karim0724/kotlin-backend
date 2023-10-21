package ru.otus.cleaning

import RepoOrderReadTest
import ru.otus.cleaning.repo.IOrderRepository
import kotlin.test.Test

class OrderRepoInMemoryReadTest : RepoOrderReadTest() {
    override val orderRepository: IOrderRepository = OrderRepoInMemory(initObjects = initObjects)
}