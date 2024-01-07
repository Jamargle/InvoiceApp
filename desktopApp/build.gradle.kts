import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
//    alias(libs.plugins.googleServices)
}

kotlin {

    jvm("desktop") {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    sourceSets {
        val desktopMain by getting {
            dependencies {
                implementation(projects.shared)
                implementation(compose.desktop.currentOs)
//                implementation(libs.firebase.admin)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.jamarglex.invoiceapp.desktop"
            packageVersion = "1.0.0"
        }
    }
}
