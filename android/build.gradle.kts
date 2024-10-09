import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.dokka.Platform
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.jetbrains.dokka)
}

android {
    namespace = "com.komoju.android.sdk"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        resourcePrefix = "komoju_"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose = true
    }
    lint {
        warningsAsErrors = true
        abortOnError = true
        disable.add("AndroidGradlePluginVersion")
    }
}

dependencies {
    api(project(":shared"))
    implementation(libs.core.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.compose.webview)
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.screenModel)
    implementation(libs.voyager.transitions)
    implementation(libs.androidx.browser)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    debugImplementation(libs.androidx.ui.tooling)
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    coordinates(groupId = "com.komoju.mobile.sdk", artifactId = "android", version = "0.0.1")
    pom {
        name.set("Komoju Mobile SDK For Android")
        description.set("Komoju Mobile SDK For Android")
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
            }
        }
        scm {
            url.set("https://github.com/degica/komoju-mobile-sdk/")
            connection.set("scm:git:git://github.com/degica/komoju-mobile-sdk.git")
            developerConnection.set("scm:git:ssh://git@github.com/degica/komoju-mobile-sdk.git")
        }
    }
}

tasks.matching { task ->
    task.name.contains("javaDocReleaseGeneration", ignoreCase = true)
}.configureEach {
    enabled = false
}

tasks.withType<DokkaTask>().configureEach {
    moduleName.set("Komoju Android SDK Documentation")
    moduleVersion.set("v0.0.1")
    outputDirectory.set(layout.projectDirectory.dir("docs/"))
    dokkaSourceSets.configureEach {
        displayName.set("Android")
        skipEmptyPackages.set(true)
        jdkVersion.set(17)
        noStdlibLink.set(false)
        noJdkLink.set(false)
        noAndroidSdkLink.set(false)
        platform.set(Platform.DEFAULT)
    }
    pluginsMapConfiguration.set(
        mapOf(
            "org.jetbrains.dokka.base.DokkaBase" to "{ \"footerMessage\": \"(c) 2024 Degica\" }".trim(),
        ),
    )
}
