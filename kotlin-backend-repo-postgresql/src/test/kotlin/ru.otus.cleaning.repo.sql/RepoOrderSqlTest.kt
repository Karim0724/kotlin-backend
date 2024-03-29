package ru.otus.cleaning.repo.sql

import RepoOrderCreateTest
import RepoOrderReadTest
import RepoOrderSearchByCompanyIdTest
import RepoOrderSearchByUserIdTest
import ru.otus.cleaning.repo.IOrderRepository

class RepoOrderSQLCreateTest : RepoOrderCreateTest() {
    override val orderRepository: IOrderRepository = SqlTestCompanion.repoUnderTestContainer(
        initObjects,
        randomUuid = { lockNew.asString() },
    )
}

class RepoOrderSQLReadTest : RepoOrderReadTest() {
    override val orderRepository: IOrderRepository = SqlTestCompanion.repoUnderTestContainer(initObjects)
}

class RepoOrderSQLSearhByCompanyIdTest : RepoOrderSearchByCompanyIdTest() {
    override val orderRepository: IOrderRepository = SqlTestCompanion.repoUnderTestContainer(initObjects)
}

class RepoOrderSQLSearchByUserIdTest : RepoOrderSearchByUserIdTest() {
    override val orderRepository: IOrderRepository = SqlTestCompanion.repoUnderTestContainer(initObjects)
}
