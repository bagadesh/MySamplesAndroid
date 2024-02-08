import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by bagadesh on 02/03/23.
 */
class CommonAndroidPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.configureAndroid()
    }
}