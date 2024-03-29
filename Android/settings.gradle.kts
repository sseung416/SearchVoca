pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "SearchVOCA"
include(":app")
include(":cardStackView")
include(":data")
include(":remote")
include(":local")
include(":domain")
include(":shared")
include(":shared:android")
include(":shared:domain")