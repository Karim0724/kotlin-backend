plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    val exposedVersion: String by project
    val postgresDriverVersion: String by project
    val kmpUUIDVersion: String by project
    val testContainersVersion: String by project

    implementation(kotlin("stdlib"))

    implementation(project(":kotlin-backend-common"))

    implementation("org.postgresql:postgresql:$postgresDriverVersion")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    implementation("com.benasher44:uuid:$kmpUUIDVersion")


    testImplementation("org.testcontainers:postgresql:$testContainersVersion")
    testImplementation(project(":kotlin-backend-repo-test"))
}