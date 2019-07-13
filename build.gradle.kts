plugins {
    id("org.jetbrains.kotlin.js") version "1.3.41"
}

group = "it.krzeminski"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
}

kotlin {
    target {
        useCommonJs()
        browser {
            // Needed to pack Kotlin standard library together with this project's code. Otherwise, only a Javascript
            // file with this project's code is generated.
            webpackTask {}
        }
    }
}

val filesToDeployDestination = "$buildDir/deploy-me"

val copyIndexHtml by tasks.registering(Copy::class) {
    from(file("$buildDir/processedResources/Js/main/index.html"))
    into(file(filesToDeployDestination))
}

val copyJavascriptBundle by tasks.registering(Copy::class) {
    from(file("$buildDir/libs/${project.name}.js"))
    into(file(filesToDeployDestination))
}

val assemble by tasks.existing {
    dependsOn(copyIndexHtml)
    dependsOn(copyJavascriptBundle)
}
