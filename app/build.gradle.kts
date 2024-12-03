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

//    signingConfigs {
//        getByName("debug") {
//            keyAlias = "debug"
//            keyPassword = "123456"
////            storeFile = file("/home/miles/keystore.jks")
//            storePassword = "123456"
//        }
//        create("release") {
//            keyAlias = "release"
//            keyPassword = "123456"
////            storeFile = file("/home/miles/keystore.jks")
//            storePassword = "123456"
//        }
//    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
//            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false
            buildConfigField("String", "BASE_URL", "\"https://kidzone.masstel.vn/\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug  {
            isMinifyEnabled = false
            isShrinkResources = false
//            signingConfig = signingConfigs.getByName("debug")
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
        buildConfig = true
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

    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    implementation (libs.retrofit)
    implementation (libs.retrofit2.kotlin.coroutines.adapter)
    //noinspection UseTomlInstead
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")

    implementation (libs.kotlin.coroutine.core)
    implementation (libs.kotlin.coroutine.android)

    //gson
    implementation (libs.gson)
    // viewmodel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.fragment.ktx)
}