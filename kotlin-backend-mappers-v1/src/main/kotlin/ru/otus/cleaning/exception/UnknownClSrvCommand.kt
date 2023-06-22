package ru.otus.cleaning.exception

import ru.otus.cleaning.models.ClSrvCommand

class UnknownClSrvCommand(command: ClSrvCommand) : Throwable("Wrong command $command at mapping toTransport stage")
