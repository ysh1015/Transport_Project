plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.hk.transportProject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hk.transportProject"
        minSdk = 28
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.retrofit)        // Retrofit 의존성 추가
    implementation(libs.retrofit.gson)   // Gson 컨버터 의존성 추가
    implementation(libs.naver.map.sdk)      // 네이버 지도 SDK 의존성 추가
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}