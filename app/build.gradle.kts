import config.ProjectConfig

plugins {
    id(AppPlugin.PLUGIN_APP)
}

android {
    namespace = ProjectConfig.appId
    buildFeatures {
        dataBinding =true
    }
}

dependencies {
    di()
    general()
    testing()
    navigation()
    kapt("androidx.room:room-compiler:2.6.1")
    //ksp("androidx.room:room-compiler:2.6.1")

    implementation(project(":core"))
    implementation(project(":feature:home"))
    implementation(project(":common:network"))

}
