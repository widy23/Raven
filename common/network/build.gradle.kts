plugins {
    id(ModulePlugin.MODULE_NAME)
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
