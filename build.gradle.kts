// Root-level build.gradle.kts

plugins {
    id("com.android.application") version "8.6.0" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://chaquo.com/maven") } // Add Chaquopy's Maven repository here
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.4.0")
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
