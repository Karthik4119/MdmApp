plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.mdmapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mdmapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}


dependencies {


    implementation ("androidx.core:core-ktx:1.13.1")
    implementation ("androidx.appcompat:appcompat:1.7.0")
    implementation ("com.google.android.material:material:1.12.0")
    implementation ("androidx.compose.ui:ui:1.7.1")
    implementation ("androidx.compose.material:material:1.7.1")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.7.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation ("androidx.activity:activity-compose:1.9.2")
    implementation ("androidx.navigation:navigation-runtime-ktx:2.8.1")
    implementation(libs.androidx.runtime.livedata)
    testImplementation ("junit:junit:4.+")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.0.1")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.7.1")
    implementation ("androidx.navigation:navigation-compose:2.8.1")
    implementation ("androidx.activity:activity-compose:1.9.2")
    implementation ("androidx.compose.material:material-icons-extended:1.0.1")
    implementation ("com.google.accompanist:accompanist-flowlayout:0.20.0")
    implementation ("androidx.core:core-splashscreen:1.0.0-rc01")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
    implementation ("androidx.compose.foundation:foundation:1.7.2")

    implementation ("com.google.accompanist:accompanist-permissions:0.36.0")
}