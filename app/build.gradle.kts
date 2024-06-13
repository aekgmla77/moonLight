plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.moonlight"
    compileSdk = 34
    buildFeatures.viewBinding = true

    defaultConfig {
        applicationId = "com.example.moonlight"
        minSdk = 34
        targetSdk = 34
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
}

dependencies {

    // gson
    implementation(libs.gson)
    // retrofit
    implementation(libs.retrofit)
    // lifecycle
    implementation(libs.androidx.livedata)
    implementation(libs.androidx.viewmodel)
    // 달력
    implementation(libs.calendarview)
    implementation(libs.threeten)
    // 네비게이션
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment)
    // xml
    implementation(libs.tick.annotation)
    implementation(libs.tick.core)
    implementation(libs.tick.retrofit)

//    implementation(libs.retrofit.xml)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.fragment.ktx)
    testImplementation(libs.junit)

    kapt(libs.tick.processor)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}