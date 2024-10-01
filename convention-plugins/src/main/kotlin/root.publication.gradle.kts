plugins {
    id("io.github.gradle-nexus.publish-plugin")
}

allprojects {
    group = "com.komoju.mobile.sdk"
    version = "0.0.1b"
}

nexusPublishing {
    // Configure maven central repository
    // https://github.com/gradle-nexus/publish-plugin#publishing-to-maven-central-via-sonatype-ossrh
    repositories {
        sonatype {
            // only for users registered in Sonatype after 24 Feb 2021
            nexusUrl.set(uri("https://central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/content/repositories/snapshots/"))
        }
    }
}
