val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val doodleVersion: String by project
val lombok_version: String by project
val jakarta_persistence_version: String by project
val lavaplayer_version: String by project
val jda_version: String by project
val dotenv_version: String by project
val commons_net: String by project
val piston4j: String by project
plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "example.com"
version = "0.0.1"

application {
    mainClass.set("example.com.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven(uri("https://m2.dv8tion.net/releases"))
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("io.github.cdimascio:dotenv-kotlin:$dotenv_version")
    implementation("net.dv8tion:JDA:$jda_version")
    implementation("com.sedmelluq:lavaplayer:$lavaplayer_version")
    implementation ("io.nacular.doodle:core:$doodleVersion")
    implementation ("io.nacular.doodle:browser:$doodleVersion")
    implementation ("io.nacular.doodle:controls:$doodleVersion")
    implementation ("io.nacular.doodle:animation:$doodleVersion")
    implementation ("io.nacular.doodle:themes:$doodleVersion")
    implementation("org.projectlombok:lombok:$lombok_version")
    implementation("jakarta.persistence:jakarta.persistence-api:$jakarta_persistence_version")
    implementation("commons-net:commons-net:$commons_net")
    // implementation("commons-validator:commons-validator:")
    implementation("com.github.the-codeboy:Piston4J:$piston4j")
}
