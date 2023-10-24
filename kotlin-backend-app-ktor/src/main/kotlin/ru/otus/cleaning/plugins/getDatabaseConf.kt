package ru.otus.cleaning.plugins

import io.ktor.server.application.*
import ru.otus.cleaning.OrderRepoInMemory
import ru.otus.cleaning.config.ConfigPaths
import ru.otus.cleaning.config.OrderDbType
import ru.otus.cleaning.config.PostgresConfig
import ru.otus.cleaning.repo.IOrderRepository
import ru.otus.cleaning.repo.sql.RepoOrderSql
import ru.otus.cleaning.repo.sql.SqlProperties
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

fun Application.getDatabaseConf(type: OrderDbType): IOrderRepository {
    val dbSettingPath = "${ConfigPaths.repository}.${type.confName}"
    return when (environment.config.propertyOrNull(dbSettingPath)?.getString()?.lowercase()) {
        "in-memory", "inmemory", "memory", "mem" -> initInMemory()
        "postgres", "postgresql", "pg", "sql", "psql" -> initPostgres()
        else -> throw IllegalArgumentException(
            "$dbSettingPath must be set in application.yml to one of: " +
                    "'inmemory', 'postgres', 'cassandra', 'gremlin'"
        )
    }
}

private fun Application.initPostgres(): IOrderRepository {
    val config = PostgresConfig(environment.config)
    return RepoOrderSql(
        properties = SqlProperties(
            url = config.url,
            user = config.user,
            password = config.password,
            schema = config.schema,
        )
    )
}

private fun Application.initInMemory(): IOrderRepository {
    val ttlSetting = environment.config.propertyOrNull("db.prod")?.getString()?.let {
        Duration.parse(it)
    }
    return OrderRepoInMemory(ttl = ttlSetting ?: 10.minutes)
}