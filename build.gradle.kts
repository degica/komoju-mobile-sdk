plugins {
    // trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.maven.publish) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.jetbrains.dokka) apply false
}
