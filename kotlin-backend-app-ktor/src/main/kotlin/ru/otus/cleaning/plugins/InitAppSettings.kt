package ru.otus.cleaning.plugins

import io.ktor.server.application.*
import ru.otus.cleaning.ClSrvCorSettings
import ru.otus.cleaning.ClSrvProcessor
import ru.otus.cleaning.config.ClSrvApplicationSetting
import ru.otus.cleaning.config.OrderDbType
import ru.otus.cleaning.repo.stub.OrderRepoStub

fun Application.initAppSettings(): ClSrvApplicationSetting {
    val corSettings = ClSrvCorSettings(
        repoTest = getDatabaseConf(OrderDbType.TEST),
        repoProd = getDatabaseConf(OrderDbType.PROD),
        repoStub = OrderRepoStub()
    )
    return ClSrvApplicationSetting(
        appUrls = environment.config.propertyOrNull("ktor.urls")?.getList() ?: emptyList(),
        processor = ClSrvProcessor(corSettings),
        corSettings = corSettings
    )
}