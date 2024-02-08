plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.bagadesh.featureflags"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Compose.composeCompilerVersion
    }
}

dependencies {
    implementation(Libs.Compose.UI)
    implementation(Libs.Compose.Activity)
    implementation(Libs.Compose.Material)
    implementation(Libs.Compose.Tooling)

    implementation(Libs.LifeCycle.Compose)
    implementation(Libs.LifeCycle.ViewModel)
    implementation(Libs.LifeCycle.Runtime)

    kapt(Libs.Hilt.Compiler)
    implementation(Libs.Hilt.Android)
    implementation(Libs.Hilt.NavigationCompose)

    implementation(Libs.Gson.gson)
    implementation(Libs.Logger.Timber)

    implementation(Libs.Preference.DataStore)
}