import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("app.cash.sqldelight") version "2.0.2"
}

kotlin {
    jvm("desktop")
    compilerOptions {
        freeCompilerArgs.add( "-Xcontext-receivers" )
    }
    sourceSets {
        val desktopMain by getting
        
        commonMain.dependencies {
            implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
        }
        desktopMain.dependencies {
            implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

compose.desktop {
    application {
        mainClass = "dbms.project.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "dbms.project"
            packageVersion = "1.0.0"
        }
    }
}

sqldelight {
    databases {
        create("TrainDatabase") {
            packageName.set("dbms.project")
        }
    }
}