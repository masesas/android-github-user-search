plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.navigation.safe.args)
}

android {
    namespace = "com.assesment.githubuser"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.assesment.githubuser"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    implementation(libs.bundles.ui)
    implementation(project.dependencies.platform(libs.core.koin.bom))
    implementation(libs.bundles.koin)

    implementation(libs.core.work.manager)
    implementation(libs.core.koin.worker)
    runtimeOnly(libs.core.koin.viewmodel)
    implementation(libs.bundles.viewmodel)
    implementation(libs.core.gson)

    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":remote"))
    implementation(project(":local"))
    implementation(project(":shared"))
}