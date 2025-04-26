plugins {
    id("widgetforge.android.library")
    id("widgetforge.android.library.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.keyraco.widgetforge.common"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.serialization)
}