plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
//    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.themovielibrary"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.themovielibrary"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding { enable = true }

    kapt {
        javacOptions {
            option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
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

    // - - ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    // - - LiveData
    implementation (libs.androidx.lifecycle.livedata.ktx)
    // - - Retrofit2
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)
    // - - Glide
    implementation (libs.glide)

    // - - Room
    implementation (libs.androidx.room.ktx)
    //noinspection KaptUsageInsteadOfKsp
    kapt (libs.androidx.room.compiler)
    androidTestImplementation (libs.androidx.room.testing)

    // - - Dagger Hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)

    implementation (libs.androidx.fragment.ktx)

    implementation("androidx.hilt:hilt-navigation-fragment:1.2.0")


}