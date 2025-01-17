plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.assesment.local"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.java.get())
    }
    kotlinOptions {
        jvmTarget = JavaVersion.toVersion(libs.versions.java.get()).toString()
    }
}

dependencies {
    implementation(libs.core.androidx.core.ktx)
    testImplementation(libs.testing.junit)
    androidTestImplementation(libs.testing.androidx.junit)
    androidTestImplementation(libs.testing.androidx.espresso.core)

    implementation(project.dependencies.platform(libs.core.koin.bom))
    implementation(libs.bundles.koin)
    implementation(project(":shared"))
    implementation(project(":data"))
    implementation(libs.bundles.caching)
    ksp(libs.caching.room.compiler)
}