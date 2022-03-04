dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
        maven {
            setUrl("https://dl.bintray.com/thelasterstar/maven/")
        }
    }
}
rootProject.name = "WanAndroid_Gank"
include(":app")
include(":base")
include(":res")
