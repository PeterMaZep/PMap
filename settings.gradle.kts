import java.io.File

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

// Read the key.txt file
val keyFile = File(rootDir, "key.txt")
// Use the token, or a dummy string if the file doesn't exist so the project can still sync
val mapboxToken = if (keyFile.exists()) keyFile.readText().trim() else "dummy-token"

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            credentials {
                username = "mapbox"
                password = mapboxToken
            }
        }
    }
}

rootProject.name = "PMap"
include(":app")