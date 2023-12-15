import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.21"

    id("io.gitlab.arturbosch.detekt") version "1.23.4"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("com.github.shiguruikai:combinatoricskt:1.6.0")
    testImplementation(kotlin("test"))
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.4")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = JvmTarget.JVM_21
        languageVersion = KotlinVersion.KOTLIN_1_9
    }
}

tasks.withType<Test> {
    useJUnitPlatform {
        excludeTags = setOf(getProperty("TEST_IGNORE_TAG", "playground"))
    }
}

detekt {
    config.from(files("detekt-config.yml"))
    buildUponDefaultConfig = true
}

fun getProperty(key: String, default: String = ""): String = System.getenv(key) ?: default
