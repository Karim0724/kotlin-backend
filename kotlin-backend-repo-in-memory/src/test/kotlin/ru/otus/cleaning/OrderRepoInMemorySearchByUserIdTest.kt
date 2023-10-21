package ru.otus.cleaning

import RepoOrderSearchByUserIdTest
import ru.otus.cleaning.repo.IOrderRepository

class OrderRepoInMemorySearchByUserIdTest : RepoOrderSearchByUserIdTest() {
    override val orderRepository: IOrderRepository = OrderRepoInMemory(initObjects = initObjects)
}