package ru.otus.cleaning

import RepoOrderCreateTest
import ru.otus.cleaning.repo.IOrderRepository

class OrderRepoInMemoryCreateTest : RepoOrderCreateTest() {
    override val orderRepository: IOrderRepository = OrderRepoInMemory(initObjects = initObjects)
}