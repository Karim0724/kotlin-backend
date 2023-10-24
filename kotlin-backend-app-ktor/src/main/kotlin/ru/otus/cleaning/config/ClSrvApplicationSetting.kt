package ru.otus.cleaning.config

import ru.otus.cleaning.ClSrvCorSettings
import ru.otus.cleaning.ClSrvProcessor

class ClSrvApplicationSetting(
    val appUrls: List<String> = emptyList(),
    val corSettings: ClSrvCorSettings = ClSrvCorSettings(),
    val processor: ClSrvProcessor = ClSrvProcessor(settings = corSettings),
)