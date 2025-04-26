plugins {
    id("widgetforge.android.library.glance")
    id("widgetforge.android.library.compose")

}

android {
    namespace = "com.keyraco.widgetforge.widgets"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)
}