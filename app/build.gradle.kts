plugins {
    id("widgetforge.android.application")
    id("widgetforge.android.application.compose")
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.keyraco.widgetforge"

    defaultConfig {
        applicationId = "com.keyraco.widgetforge"
        versionCode = 1
        versionName = "0.1.0"
        setProperty("archivesBaseName", "Widget-Forge-v$versionName")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            enableUnitTestCoverage = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    flavorDimensionList += "version"
    productFlavors {
        create("dev") {
            dimension = "version"
            applicationIdSuffix = ".dev"
            versionNameSuffix ="-dev"
            signingConfig = signingConfigs.getByName("debug")
        }
        create("store") {
            dimension = "version"
        }
    }

    packaging {
        resources.excludes.add("META-INF/*")
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.ui)
    implementation(projects.feature.home)
    implementation(projects.feature.builder)
    implementation(projects.feature.widgets)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.lifecycle.process)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.materialicons)
    implementation(libs.androidx.graphics.shapes)
}