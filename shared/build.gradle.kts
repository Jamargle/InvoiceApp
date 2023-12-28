import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.libRes)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    jvm("desktop") {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.compose.desktop.material3)
                implementation(libs.kotlinx.coroutines.swing)
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                api(libs.koin.core)
                api(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization)
                implementation(libs.libres)
                implementation(libs.voyager.koin)
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.screenModel)
            }
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        val commonMobileMain by creating {
            dependsOn(commonMain)
        }

        androidMain.get().apply {
            dependsOn(commonMobileMain)
            dependencies {
                implementation(libs.compose.ui.tooling.preview)
                implementation(libs.androidx.activity.compose)
                implementation(libs.kotlinx.coroutines.android)
            }
        }

        iosMain.get().apply {
            dependsOn(commonMobileMain)
        }
    }
}

android {
    namespace = "com.jamarglex.invoiceapp.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

}

libres {
    generateNamedArguments = true // false by default
}
