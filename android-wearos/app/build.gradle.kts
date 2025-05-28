plugins { 
    id("com.android.application") 
    id("com.google.gms.google-services") 
    id("org.jetbrains.kotlin.android") 
} 
 
android { 
    namespace = "com.imsec.silentbull" 
    compileSdk = 34 
 
    defaultConfig { 
        applicationId = "com.imsec.silentbull" 
        minSdk = 26 
        targetSdk = 34 
        versionCode = getVersionCode()
        versionName = getVersionName()
    } 
 
    signingConfigs { 
        create("release") { 
            storeFile = file("../silentbull-release-key.keystore") 
            storePassword = System.getenv("KEYSTORE_PASSWORD") 
            keyAlias = System.getenv("KEY_ALIAS") 
            keyPassword = System.getenv("KEY_PASSWORD") 
        } 
    } 
 
    buildTypes { 
        getByName("release") { 
            isMinifyEnabled = false 
            isShrinkResources = false 
            proguardFiles( 
                getDefaultProguardFile("proguard-android-optimize.txt"), 
                "proguard-rules.pro" 
            ) 
            signingConfig = signingConfigs.getByName("release") 
        } 
    } 
 
    compileOptions { 
        sourceCompatibility = JavaVersion.VERSION_1_8 
        targetCompatibility = JavaVersion.VERSION_1_8 
    } 
 
    kotlinOptions { 
        jvmTarget = "1.8" 
    } 
} 

fun getVersionCode(): Int {
    // GitHub Actions 환경에서는 GITHUB_RUN_NUMBER 사용
    val runNumber = System.getenv("GITHUB_RUN_NUMBER")
    if (runNumber != null) {
        return runNumber.toInt()
    }
    
    // 로컬 환경에서는 Git commit count 사용
    return try {
        val stdout = ByteArrayOutputStream()
        exec {
            commandLine = listOf("git", "rev-list", "--count", "HEAD")
            standardOutput = stdout
            isIgnoreExitValue = true
        }
        stdout.toString().trim().toIntOrNull() ?: 1
    } catch (e: Exception) {
        println("Git commit count failed, using default: 1")
        1
    }
}

fun getVersionName(): String {
    // GitHub Actions 환경에서는 GITHUB_SHA 사용
    val githubSha = System.getenv("GITHUB_SHA")
    val runNumber = System.getenv("GITHUB_RUN_NUMBER")
    
    if (githubSha != null && runNumber != null) {
        return "1.0.${runNumber}-${githubSha.take(7)}"
    }
    
    // 로컬 환경에서는 Git describe 사용
    return try {
        val stdout = ByteArrayOutputStream()
        exec {
            commandLine = listOf("git", "describe", "--tags", "--always")
            standardOutput = stdout
            isIgnoreExitValue = true
        }
        val gitDesc = stdout.toString().trim()
        if (gitDesc.isNotEmpty()) gitDesc else "1.0.0-dev"
    } catch (e: Exception) {
        println("Git describe failed, using default version")
        "1.0.0-dev"
    }
}
 
dependencies { 
    implementation("androidx.core:core-ktx:1.12.0") 
    implementation("androidx.activity:activity-ktx:1.8.2") 
 
    // Wear OS specific dependencies 
    implementation("androidx.wear:wear:1.3.0") 
    implementation("com.google.android.support:wearable:2.9.0") 
 
    implementation(platform("com.google.firebase:firebase-bom:33.14.0")) 
    implementation("com.google.firebase:firebase-analytics") 
 
    compileOnly("com.google.android.wearable:wearable:2.9.0") 
}
