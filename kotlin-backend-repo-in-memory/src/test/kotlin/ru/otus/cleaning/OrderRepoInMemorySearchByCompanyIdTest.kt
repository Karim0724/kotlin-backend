package ru.otus.cleaning

import RepoOrderSearchByCompanyIdTest
import ru.otus.cleaning.repo.IOrderRepository

class OrderRepoInMemorySearchByCompanyIdTest : RepoOrderSearchByCompanyIdTest() {
    override val orderRepository: IOrderRepository = OrderRepoInMemory(initObjects = initObjects)
}