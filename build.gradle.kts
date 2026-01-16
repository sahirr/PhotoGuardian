// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Safe args for navigation if needed later, otherwise optional
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.6")
    }
}

plugins {
    // Android Gradle Plugin - Application (Match with Gradle 8.2.1+)
    id("com.android.application") version "8.2.1" apply false
    
    // Android Gradle Plugin - Library
    id("com.android.library") version "8.2.1" apply false
    
    // Kotlin Android
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    
    // Hilt Dependency Injection
    id("com.google.dagger.hilt.android") version "2.50" apply false
    
    // KSP (Kotlin Symbol Processing) - Must match Kotlin version!
    // Kotlin 1.9.22 -> KSP 1.9.22-1.0.17
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
}