package ru.otus.cleaning

import operation
import ru.otus.cleaning.models.ClSrvCommand
import ru.otus.cleaning.workers.initStatus
import ru.otus.cleaning.workers.stubCreateSuccess
import ru.otus.cleaning.workers.stubNoCase
import ru.otus.otuskotlin.marketplace.cor.rootChain
import stubFindByCompanyIdSuccess
import stubFindByUserIdSuccess
import stubReadSuccess
import stubValidationBadCompanyId
import stubValidationBadDateTime
import stubValidationBadUserId
import stubValidationNotFound
import stubs

class ClSrvProcessor {
    suspend fun process(ctx: ClSrvContext) = BusinessChain.exec(ctx)

    companion object {
        private val BusinessChain = rootChain {
            initStatus("Инициализация контекста")
            operation("Создание заказа", ClSrvCommand.CREATE) {
                stubs(title = "Обработка стабов") {
                    stubCreateSuccess("Имитация создания заказа")
                    stubValidationBadDateTime("Имитация неправильной даты заказа")
                    stubValidationBadCompanyId("Имитация неправльного идентификатора компании")
                    stubValidationBadUserId("Имитация неправильного идентификатороа пользователя")
                    stubNoCase("Ошибка: Запрошенный stub отсутствует")
                }
            }

            operation("Получение заказа", ClSrvCommand.READ) {
                stubs(title = "Обработка стабов") {
                    stubReadSuccess("Получение заказа по идентификатору")
                    stubValidationNotFound("Имитация отсутстие заказа")
                    stubNoCase("Ошибка: Запрошенный stub отсутствует")
                }
            }

            operation("Получение заказов компании", ClSrvCommand.SEARCH_BY_COMPANY_ID) {
                stubs(title = "Обработка стабов") {
                    stubFindByCompanyIdSuccess("Получение заказов по идентификатору компании")
                    stubValidationNotFound("Имитация отсутстия компании")
                    stubValidationBadCompanyId("Имитация неправльного идентификатора компании")
                    stubNoCase("Ошибка: Запрошенный stub отсутствует")
                }
            }

            operation("Получение заказов пользователя", ClSrvCommand.SEARCH_BY_USER_ID) {
                stubs(title = "Обработка стабов") {
                    stubFindByUserIdSuccess("Получение заказов по идентификатору клиента")
                    stubValidationNotFound("Имитация отсутстия пользователя")
                    stubValidationBadUserId("Имитация неправильного идентификатороа пользователя")
                    stubNoCase("Ошибка: Запрошенный stub отсутствует")
                }
            }
        }.build()
    }
}