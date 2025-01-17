plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.assesment.remote"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.core.androidx.core.ktx)
    testImplementation(libs.testing.junit)
    androidTestImplementation(libs.testing.androidx.junit)
    androidTestImplementation(libs.testing.androidx.espresso.core)

    releaseImplementation(libs.networking.chucker.release)
    debugImplementation(libs.networking.chucker.debug)

    implementation(project.dependencies.platform(libs.core.koin.bom))
    implementation(libs.bundles.koin)
    implementation(libs.bundles.networking)
    implementation(libs.core.androidx.core.ktx)
    implementation(project(":shared"))
    implementation(project(":data"))
}