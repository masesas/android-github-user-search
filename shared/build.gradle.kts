plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.assesment.shared"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.core.androidx.core.ktx)
    testImplementation(libs.testing.junit)
    androidTestImplementation(libs.testing.androidx.junit)
    androidTestImplementation(libs.testing.androidx.espresso.core)

    implementation(libs.core.androidx.core.ktx)
    implementation(libs.bundles.coroutines)
    implementation(libs.ui.androidx.appcompat)
    implementation(libs.ui.recyclerview)
    implementation(libs.ui.swiperefreshlayout)
    implementation(libs.bundles.viewmodel)
}