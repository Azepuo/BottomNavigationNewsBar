plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.bottomnavigationnewsbar"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.bottomnavigationnewsbar"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.navigation:navigation-compose:2.8.9")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Retrofit & Gson
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3") // Version suggérée

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // ViewModel
    implementation ("androidx.navigation:navigation-compose:2.8.9" )
    implementation ("androidx.compose.runtime:runtime-livedata:1.8.0" )
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.1")
    // pour collectAsStateWithLifecycle

    // Jetpack Compose
    implementation ("androidx.compose.ui:ui:1.6.0")
    implementation ("androidx.compose.material:material:1.6.0") // ou material3
    implementation ("androidx.compose.ui:ui-tooling-preview:1.6.0")
    implementation ("androidx.activity:activity-compose:1.8.2")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.6.0")

    // Compose Navigation si ce n est pas deja ds le projet
   // implementation ("androidx.navigation:navigation-compose:2.7.6")

    // Coil (pour le chargement d'images dans Compose)
    implementation ("io.coil-kt:coil-compose:2.5.0")

    // Gson (pour la sérialisation/désérialisation si besoin pour la navigation)
    implementation ("com.google.code.gson:gson:2.10.1")
}