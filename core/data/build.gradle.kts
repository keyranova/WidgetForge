plugins {
    id("widgetforge.android.library")
    id("widgetforge.android.library.compose")
    id("widgetforge.android.room")
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.keyraco.widgetforge.data"
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.dataStore.core)
    implementation(libs.androidx.dataStore.preferences)
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.datetime)
}