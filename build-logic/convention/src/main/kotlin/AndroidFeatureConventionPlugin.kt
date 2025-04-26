import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            pluginManager.apply {
                apply("widgetforge.android.library")
                apply("widgetforge.android.library.compose")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }

            dependencies {
                add(configurationName = "implementation", project(":core:ui"))
                add(configurationName = "implementation", project(":core:data"))
                add(configurationName = "implementation", project(":core:common"))

                add(configurationName = "implementation", libs.findLibrary("androidx.navigation.compose").get())
                add(configurationName = "implementation", libs.findLibrary("androidx.lifecycle.runtime.compose").get())
            }
        }
    }
}