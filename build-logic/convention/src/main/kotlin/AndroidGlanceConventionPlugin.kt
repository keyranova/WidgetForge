import com.android.build.gradle.LibraryExtension
import com.keyraco.widgetforge.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.dependencies

class AndroidGlanceConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(commonExtension = this)
            defaultConfig.targetSdk = libs.findVersion("androidTargetSdk").get().requiredVersion.toInt()
        }

        dependencies {
            add(configurationName = "implementation", libs.findLibrary("androidx.glance.appwidget").get())
            add(configurationName = "implementation", libs.findLibrary("androidx.glance.material3").get())
            add(configurationName = "implementation", libs.findLibrary("koin.android").get())
        }
    }
}