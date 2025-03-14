plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "2.1.10"
    id("com.gradle.plugin-publish") version "1.3.1"
}

group = "io.github.vengateshm"
version = "0.1"

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
            implementationClass = "dev.vengateshm.retrofit_code_gen.RetrofitCodeGenPlugin"
            displayName = "retrofitCodeGen Plugin"
            description = "retrofitCodeGen is a Gradle plugin that automates the generation of Retrofit API interfaces and Kotlin data models from JSON configuration files. It streamlines API integration by handling multiple HTTP methods (GET, POST, PUT, DELETE) and enforces a structured package organization for maintainability. Built with KotlinPoet and Kotlin Serialization, It simplifies API development, reducing boilerplate and improving code consistency."
            tags.set(listOf("gradle-plugin", "retrofit", "api-generator", "kotlin", "kotlinpoet", "serialization", "networking", "rest-api", "automation", "code-generation"))
        }
    }
}

dependencies {
    //noinspection UseTomlInstead
    implementation("com.squareup:kotlinpoet:2.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
}