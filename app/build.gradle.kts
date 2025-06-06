plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("kotlin-kapt")

}

android {
    namespace = "com.example.flook"
    compileSdk = 35

    buildFeatures {
        viewBinding = true
        buildConfig = true

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        kotlinOptions {
            jvmTarget = "11"
        }
    }

    defaultConfig {
        applicationId = "com.example.flook"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        debug {
            buildConfigField("boolean", "DEBUG", "true")
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // https://mvnrepository.com/artifact/com.airbnb.android/lottie
    implementation(libs.lottie)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.retrofit)
    // https://mvnrepository.com/artifact/com.artisan-software.glide/desktop
    implementation(libs.glide)
// https://mvnrepository.com/artifact/com.squareup.okhttp3/logging-interceptor
    implementation (libs.converter.gson)
    implementation (libs.okhttp3.logging.interceptor)

    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-beta01")

    implementation ("com.google.dagger:dagger:2.56.2")
    kapt ("com.google.dagger:dagger-compiler:2.56.2")
    implementation ("androidx.core:core-ktx:1.16.0")

    implementation ("androidx.room:room-runtime:2.7.1")
    kapt ("androidx.room:room-compiler:2.7.1")
}