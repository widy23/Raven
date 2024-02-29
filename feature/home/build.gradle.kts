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
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    di()
    general()
    testing()
    network()

    implementation(project(":core"))
}
