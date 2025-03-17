plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "2.1.10"
    id("com.gradle.plugin-publish") version "1.3.1"
    id("maven-publish")
}

val artifactVersion = "1.0.0"

group = "io.github.vengateshm"
version = artifactVersion

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

gradlePlugin {
    website.set("https://github.com/vengateshm/retrofitCodeGen")
    vcsUrl.set("https://github.com/vengateshm/retrofitCodeGen")

    plugins {
        create("retrofitCodeGen") {
            id = "io.github.vengateshm.retrofitCodeGen"
            implementationClass = "dev.vengateshm.retrofitcodegen.RetrofitCodeGenPlugin"
            displayName = "retrofitCodeGen Plugin"
            description = "A Gradle plugin that automates the generation of Retrofit API code"
            tags.set(
                listOf("gradle-plugin", "retrofit", "api-code-generator", "kotlin"),
            )
        }
    }
}

dependencies {
    //noinspection UseTomlInstead
    implementation("com.squareup:kotlinpoet:2.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
}