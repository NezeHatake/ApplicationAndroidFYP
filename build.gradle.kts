// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) version "1.9.20" apply false
    id("com.chaquo.python") version "15.0.1" apply false
}


buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Android Gradle Plugin version
        classpath("com.android.tools.build:gradle:8.6.0")

        // Kotlin Gradle Plugin version
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20") // Ensure compatibility with Compose Compiler
    }
}


