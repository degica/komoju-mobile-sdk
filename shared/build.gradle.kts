
import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.composeMultiplatform)
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            xcf.add(this)
            baseName = "komojuShared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.jetbrains.compose.lifecycle)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)

            implementation(libs.voyager.navigator)
            implementation(libs.voyager.screenModel)
            implementation(libs.voyager.transitions)

            implementation(libs.compose.webview.multiplatform)

            implementation(libs.human.readable)
            implementation(libs.uri.kmp)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.androidx.browser)
            implementation(libs.androidx.compose.ui.tooling.preview)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.komoju.mobile.sdk"
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()
    defaultConfig {
        minSdk =
            libs.versions.android.minSdk
                .get()
                .toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    resourcePrefix = "komoju_"
}

dependencies {
    debugImplementation(libs.androidx.ui.tooling)
}

compose {
    resources {
        generateResClass = never
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    coordinates(groupId = "com.komoju.mobile.sdk", artifactId = "shared", version = (System.getenv("SDK_VERSION") ?: "0.0.1"))
    pom {
        name.set("Komoju Mobile SDK")
        description.set("Komoju Payment SDK for Mobile")
        inceptionYear.set("2024")
        url.set("https://github.com/degica/komoju-mobile-sdk/")
        licenses {
            license {
                name.set("MIT")
                url.set("https://github.com/degica/komoju-mobile-sdk/blob/main/LICENSE")
                distribution.set("repo")
            }
        }
        developers {
            developer {
                id.set("AmniX")
                name.set("Aman Tonk")
                url.set("https://github.com/AmniX/")
                email.set("atonk@degica.com")
                organization.set("Degica Co., Ltd.")
                organizationUrl.set("https://degica.jp/")
                roles.set(listOf("Developer", "Maintainer", "Contributor"))
                timezone.set("Asia/Tokyo")
            }
        }
        scm {
            url.set("https://github.com/degica/komoju-mobile-sdk/")
            connection.set("scm:git:git://github.com/degica/komoju-mobile-sdk.git")
            developerConnection.set("scm:git:ssh://git@github.com/degica/komoju-mobile-sdk.git")
        }
    }
}
