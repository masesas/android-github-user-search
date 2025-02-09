plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.assesment.data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.networking)
    implementation(project(":domain"))
    implementation(project(":shared"))
    implementation(libs.core.androidx.core.ktx)
}