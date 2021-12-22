import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    `java-library`
    // `maven-publish`
    id("io.papermc.paperweight.userdev") version "1.3.2"
    id("xyz.jpenilla.run-paper") version "1.0.6"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "xyz.regulad"
version = "1.0.0-SNAPSHOT"
description = "A template plugin for Minecraft Paper Plugins with Gradle."

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

dependencies {
    paperDevBundle("1.18.1-R0.1-SNAPSHOT")

    implementation("org.bstats", "bstats-bukkit", "2.2.1")
    compileOnly("org.jetbrains", "annotations", "23.0.0")
}

tasks {
    // Configure reobfJar to run when invoking the build task
    build {
        dependsOn(reobfJar)
    }

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
        fun reloc(pkg: String) = relocate(pkg, "xyz.regulad.bukkitgradletemplate.dependency.$pkg")

        reloc("org.bstats")
    }
}

/*
publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/regulad/BukkitGradleTemplate")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}
*/

bukkit {
    name = "BukkitTemplate"
    main = "xyz.regulad.bukkitgradletemplate.BukkitTemplate"
    apiVersion = "1.18"
    authors = listOf("regulad")
    libraries = listOf("org.jetbrains:annotations:23.0.0")
}
