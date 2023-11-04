plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

apply("../shared_dependencies.kts")

android {
    namespace = "com.fahmiproduction.githubappcleanarch"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fahmiproduction.githubappcleanarch"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    dynamicFeatures += setOf(":favorite")
}

dependencies {
    implementation(project(":core"))
}