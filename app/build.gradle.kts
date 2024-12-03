import java.text.SimpleDateFormat
import java.util.Date

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.thecoach"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.thecoach"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "debug"
            keyPassword = "123456"
            storeFile = file("/home/miles/keystore.jks")
            storePassword = "123456"
        }
        create("release") {
            keyAlias = "release"
            keyPassword = "123456"
            storeFile = file("/home/miles/keystore.jks")
            storePassword = "123456"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false
            buildConfigField("String", "BASE_URL", "\"https://kidzone.masstel.vn/\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug")   {
            isMinifyEnabled = false
            isShrinkResources = false
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
            buildConfigField("String", "BASE_URL", "\"https://kidzone.masstel.vn/\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        //noinspection DataBindingWithoutKapt
        dataBinding = true
        viewBinding = true
    }

    val timestamp =
        SimpleDateFormat("MM-dd-yyyy_hh-mm").format(Date())
    // Đặt tên apk
    applicationVariants.all { variant ->
        variant.outputs.all {
            val flavor = variant.name
            val versionName = variant.versionName
            val outputFileName = "${timestamp}_10pro_${flavor}_${versionName}.apk"
        }
        true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")

    implementation (libs.kotlin.coroutine.core)
    implementation (libs.kotlin.coroutine.android)

    //gson
    implementation (libs.gson)
}