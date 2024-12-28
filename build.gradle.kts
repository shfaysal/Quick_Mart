// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.serialization") version "1.8.22"
}

//buildscript {
//    dependencies {
//        classpath("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
//    }
//
//    repositories {
//        mavenCentral()
//    }
//}