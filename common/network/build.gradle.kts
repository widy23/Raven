plugins {
    id(ModulePlugin.MODULE_NAME)
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false

}

android {
    namespace = "com.raven.network"
    buildFeatures {
        dataBinding=true
    }
}

dependencies {
    implementation(project(":feature:home"))
    di()
    general()
    network()
    navigation()

}
