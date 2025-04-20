import com.android.build.gradle.LibraryExtension
import com.keyraco.widgetforge.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(commonExtension = extension)

            dependencies {
                add(configurationName = "implementation", libs.findLibrary("androidx.activity.compose").get())
                add(configurationName = "implementation", libs.findLibrary("androidx.lifecycle.viewmodelcompose").get())
                add(configurationName = "implementation", libs.findLibrary("koin.androidxcompose").get())
            }
        }
    }
}