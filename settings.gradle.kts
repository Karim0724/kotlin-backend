rootProject.name = "kotlin-backend"

pluginManagement {
    val kotlinVersion: String by settings
    val openapiVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion apply false
        id("org.openapi.generator") version openapiVersion apply false
    }
}
//include("hw1")
include("kotlin-backend-api-v1-jackson")
include("kotlin-backend-common")