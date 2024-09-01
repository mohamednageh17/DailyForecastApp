plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.dailyforecastapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.dailyforecastapp"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "FORECAST_BASE_URL", "\"https://api.openweathermap.org/data/2.5/\"")
            buildConfigField("String", "FORECAST_APP_ID", "\"991e98ffe7c41e5bb6b84456608bb7df\"")
            buildConfigField("String", "CITIES_BASE_URL", "\"https://dev-orcas.s3.eu-west-1.amazonaws.com/\"")
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
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
    packaging {
        resources {
            // Exclude the problematic META-INF/gradle/incremental.annotation.processors path
            resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

kapt {
    correctErrorTypes = true
}


dependencies {

    implementation(
        project.project(":data")
    )

    implementation(
        project.project(":domain")
    )

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation (libs.truth)
    androidTestImplementation (libs.truth)

    testImplementation (libs.mockito.mockito.core)
    androidTestImplementation (libs.mockito.mockito.core)

    testImplementation(libs.mockito.kotlin)

    //hilt
    implementation(libs.dagger.hilt.core)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.fragment)
    implementation(libs.hilt.compiler)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.gson)

    //Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    //room
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    //room
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    kapt("androidx.room:room-compiler:2.6.1")

    //chucker
    debugImplementation(libs.library)
    releaseImplementation(libs.library.no.op)

}