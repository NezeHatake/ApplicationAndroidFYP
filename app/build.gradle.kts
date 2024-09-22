plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.newapps"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.newapps"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        }

    buildFeatures {
        compose = true
        dataBinding = true
        viewBinding = true
    }

    }




dependencies {
    // Core AndroidX dependencies
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.activity:activity:1.9.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.fragment:fragment:1.5.0")
    // ZXing libraries for QR code scanning
    implementation("com.google.zxing:core:3.5.3")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation(libs.mediarouter)

    // AndroidX Test dependencies
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // Or the latest version
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // Or the latest version
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")

    // JUnit dependency for local unit tests
    testImplementation ("junit:junit:4.13.2")
}

