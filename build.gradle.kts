
buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://maven.google.com/")
        }
    }
    dependencies {
        classpath(Libs.Android.AGP)
        classpath(Libs.Kotlin.GradlePlugin)
        classpath(Libs.Kotlin.Reflect)
        classpath(Libs.Hilt.Plugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    }
}

tasks.register("clean",Delete::class) {
    delete(rootProject.buildDir)
}