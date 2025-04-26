plugins {
    id("widgetforge.android.library")
    id("widgetforge.android.library.compose")
}

android {
    namespace = "com.keyraco.widgetforge.ui"
}

dependencies {
    implementation(libs.androidx.navigation.compose)
}