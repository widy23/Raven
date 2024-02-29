import ext.implementation

plugins {
    id(ModulePlugin.MODULE_NAME)
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.raven.home"

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    di()
    general()
    testing()
    network()

    implementation(project(":core"))
}
