pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        maven(url = "https://jitpack.io")
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven {
            url = uri("https://maven.pkg.github.com/vengateshm/retrofitCodeGen")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: ""
                password =
                    System.getenv("GITHUB_TOKEN") ?: ""
            }
        }
    }
}

rootProject.name = "AutoGenerateRetrofitAPICode"
include(":app")
include(":retrofitcodegen")
