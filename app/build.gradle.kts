plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}


android {
    namespace = "com.bagadesh.myallsampleapps"
    compileSdk = 34
    compileSdkVersion = "android-Tiramisu"
    defaultConfig {
        compileSdkVersion = "android-R"
        compileSdk = 34
        applicationId = "com.bagadesh.myallsampleapps"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isDebuggable = false
            isShrinkResources = true
            isMinifyEnabled = true
            multiDexEnabled = true
            proguardFiles.addAll(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), file("proguard-rules.pro")))
        }
        create("benchmark") {
            signingConfig = signingConfigs["debug"]
            matchingFallbacks.add("release")
            isDebuggable = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Compose.composeCompilerVersion
    }
//    packagingOptions {
//        resources {
//            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
//        }
//    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    val compose_version = Libs.Compose.compose_version
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.ui:ui-viewbinding:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation("androidx.compose.material3:material3:1.1.1")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
    implementation("androidx.activity:activity-compose:1.5.1")
     val Material = "androidx.compose.material:material:$compose_version"
    implementation(Material)


    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0")
    implementation("androidx.fragment:fragment-ktx:1.5.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("com.google.android.material:material:1.4.+")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    val nav_version = "2.5.1"

    // Jetpack Compose Integration
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // Java language implementation
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    implementation("androidx.appcompat:appcompat:1.3.0")

    val paging_version = "3.1.1"

    implementation("androidx.paging:paging-runtime:$paging_version")

    // alternatively - without Android dependencies for tests
    testImplementation("androidx.paging:paging-common:$paging_version")

    implementation("androidx.core:core-splashscreen:1.0.0")

    implementation("io.reactivex.rxjava3:rxkotlin:3.0.1")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.1")

    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("com.google.dagger:hilt-compiler:2.44")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("com.jakewharton.rxrelay3:rxrelay:3.0.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx3:1.6.4")

    implementation(project(":features:exposedcomponents"))
    implementation(project(":features:featureFlags"))


    val appcompat_version = "1.6.1"

    implementation("androidx.appcompat:appcompat:$appcompat_version")

    implementation("com.google.android.material:material:1.9.0")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

    implementation("io.github.oleksandrbalan:programguide:1.4.0")

    implementation ("com.jakewharton.timber:timber:5.0.1")


}