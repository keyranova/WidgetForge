plugins {
    id("widgetforge.android.feature")
}

android {
    namespace = "com.keyraco.widgetforge.builder"
}

dependencies {
    implementation(projects.feature.widgets)

    implementation(libs.androidx.glance.appwidget)
}
