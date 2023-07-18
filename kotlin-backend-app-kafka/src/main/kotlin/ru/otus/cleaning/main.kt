package ru.otus.cleaning

fun main() {
    val config = AppKafkaConfig()
    val listener = OrderListener(config = config, processor = ClSrvProcessor())
    listener.run()
}