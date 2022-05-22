import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm") version "1.6.21"
    id("org.jmailen.kotlinter") version "3.10.0"
    id("pl.allegro.tech.build.axion-release") version "1.13.7"
    id("se.svt.oss.gradle-yapp-publisher") version "0.1.18"
    id("se.ascp.gradle.gradle-versions-filter") version "0.1.16"
}

group = "se.svt.oss"
project.version = scmVersion.version

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

configurations.all {
    exclude(module = "junit")
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(11))
    }
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-reflect:1.6.21")
    api("org.springframework:spring-context:5.3.20")

    testImplementation("org.assertj:assertj-core:3.22.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.springframework:spring-test:5.3.20")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
    gradleVersion = "7.3.3"
    // Same issue as described in link, with kotlin-reflect and 7.4.x. Will report
    // https://github.com/ben-manes/gradle-versions-plugin/issues/471
}
