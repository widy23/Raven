plugins {
    id(ModulePlugin.MODULE_NAME)
}

android {
    namespace = "com.raven.core"
    buildFeatures {
        dataBinding =true
    }
}

dependencies {
    di()
    general()
    testing()


}
