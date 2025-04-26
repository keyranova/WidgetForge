import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.keyraco.widgetforge.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "widgetforge.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "widgetforge.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "widgetforge.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "widgetforge.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidRoom") {
            id = "widgetforge.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "widgetforge.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "widgetforge.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidGlance") {
            id = "widgetforge.android.library.glance"
            implementationClass = "AndroidGlanceConventionPlugin"
        }
    }
}