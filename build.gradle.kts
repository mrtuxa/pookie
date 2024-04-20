val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

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
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    implementation("net.dv8tion:JDA:5.0.0-beta.20")
    implementation("club.minnced:jda-ktx:0.11.0-beta.20")
    implementation("com.sedmelluq:lavaplayer:1.3.77")
    val doodleVersion = "0.10.0"
    implementation ("io.nacular.doodle:core:$doodleVersion"   )
    implementation ("io.nacular.doodle:browser:$doodleVersion")
    implementation ("io.nacular.doodle:controls:$doodleVersion" )
    implementation ("io.nacular.doodle:animation:$doodleVersion")
    implementation ("io.nacular.doodle:themes:$doodleVersion"   )

}
