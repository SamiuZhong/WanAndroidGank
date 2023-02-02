tasks.register<Delete>(name = "clean") {
    group = "build"
    delete(rootProject.buildDir)
}