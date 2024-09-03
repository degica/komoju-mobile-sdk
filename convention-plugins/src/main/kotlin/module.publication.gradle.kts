import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
    signing
}

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })

        // Provide artifacts information required by Maven Central
        pom {
            name.set("Komoju Mobile SDK")
            description.set("The Komoju Payment SDK for Android")
            url.set("https://github.com/degica/komoju-mobile-sdk")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://github.com/degica/komoju-mobile-sdk/blob/main/LICENSE")
                }
            }
            developers {
                developer {
                    id.set("AmniX")
                    name.set("Aman Tonk")
                    organization.set("Degica")
                    organizationUrl.set("https://degica.com")
                }
            }
            scm {
                url.set("https://github.com/degica/komoju-mobile-sdk")
            }
        }
    }
}

signing {
    if (project.hasProperty("signing.gnupg.keyName")) {
        useGpgCmd()
        sign(publishing.publications)
    }
}
