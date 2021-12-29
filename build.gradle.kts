plugins {
    `java-library`
    // `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "xyz.regulad"
version = "1.0.0-SNAPSHOT"
description = "A template for Minecraft Velocity Proxy Plugins with Gradle."

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    maven {
        url = uri("https://repo.velocitypowered.com/snapshots/")
    }
}

dependencies {
    compileOnly("com.velocitypowered", "velocity-api", "3.1.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered", "velocity-api", "3.1.0-SNAPSHOT")

    implementation("org.bstats", "bstats-velocity", "2.2.1")
    compileOnly("org.jetbrains", "annotations", "23.0.0")
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }

    shadowJar {
        fun reloc(pkg: String) = relocate(pkg, "${rootProject.group}.${rootProject.name}.dependency.$pkg")

        reloc("org.bstats")
    }
}
