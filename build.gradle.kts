import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.8.0-RC"
  application
  id("com.github.ben-manes.versions") version "0.44.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("net.java.dev.jna:jna:5.12.1")
    implementation("com.alphacephei:vosk:0.3.38")
}

tasks.test {
    useJUnitPlatform()
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
}

tasks {
  compileKotlin {
    kotlinOptions.jvmTarget = "17"
  }
}

application {
    mainClass.set("MainKt")
}