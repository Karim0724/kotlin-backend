package ru.otus.cleaning

import ru.otus.cleaning.repo.IOrderRepository


data class ClSrvCorSettings(
    val repoStub: IOrderRepository = IOrderRepository.NONE,
    val repoTest: IOrderRepository = IOrderRepository.NONE,
    val repoProd: IOrderRepository = IOrderRepository.NONE,
) {
    companion object {
        val NONE = ClSrvCorSettings()
    }
}
