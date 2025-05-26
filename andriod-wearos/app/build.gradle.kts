plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.silentbull"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.silentbull"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        wearableApp {
            enabled = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation("androidx.wear:wear:1.2.0")
    implementation("androidx.core:core-ktx:1.12.0")
}

