import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") apply false
}

allprojects {
    group = "ru.otus"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        google()
        maven { url = uri("https://jitpack.io") }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
}