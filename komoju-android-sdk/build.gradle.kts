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
    implementation(project(":shared"))
    implementation(libs.core.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity.compose)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.screenModel)
    implementation(libs.ui.graphics)
    implementation(libs.material3)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    debugImplementation(libs.androidx.ui.tooling)
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    coordinates(groupId = "com.komoju.mobile.sdk", artifactId = "android", version = (System.getenv("SDK_VERSION") ?: "0.0.1"))
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

tasks.matching { task ->
    task.name.contains("javaDocReleaseGeneration", ignoreCase = true)
}.configureEach {
    enabled = false
}

tasks.withType<DokkaTask>().configureEach {
    moduleName.set("Komoju Android SDK Documentation")
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
