@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")

    kotlin("android")
    kotlin("plugin.serialization")
    kotlin("kapt")
}
android {
    namespace = "com.emadmehrez.tenki"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.emadmehrez.tenki"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        /*create("benchmark") {
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
        }*/
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val composeBOM = platform("androidx.compose:compose-bom:2023.05.01")
    implementation(composeBOM)
    androidTestImplementation(composeBOM)

    val hiltVersion = "2.45"
    val roomVersion = "2.5.1"
    val coroutinesVersion = "1.6.4"
    val navVersion = "2.5.3"
    val workVersion = "2.8.1"
    val lifecycleVersion = "2.6.1"

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    // Integration with Activities
    implementation("androidx.activity:activity-compose:1.7.2")
    // Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    // Layout and Measurement API
    implementation("androidx.compose.ui:ui")
    // To create custom graphics and animations
    implementation("androidx.compose.ui:ui-graphics")
    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    // Material Design 3
    implementation("androidx.compose.material3:material3")
    // Full set of material icons
    implementation("androidx.compose.material:material-icons-extended")
    // Window size utils
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.window:window:1.0.0")
    // Tests
    testImplementation("junit:junit:4.13.2")
    // UI Tests
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // Baseline Profiles
    implementation("androidx.profileinstaller:profileinstaller:1.3.1")
    // Location Services
    implementation("com.google.android.gms:play-services-location:21.0.1")
    // Accompanist library for Permission handling in Jetpack Compose
    implementation("com.google.accompanist:accompanist-permissions:0.31.0-alpha")
    // Accompanist library for adaptive layouts (to use dual pane screens)
    implementation("com.google.accompanist:accompanist-adaptive:0.30.1")
    // Immutable kotlinx collections
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    // Collect State with Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")
    // Navigation
    implementation("androidx.navigation:navigation-compose:$navVersion")
    androidTestImplementation("androidx.navigation:navigation-testing:$navVersion")
    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    testImplementation("androidx.room:room-testing:$roomVersion")
    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    // WorkManager
    implementation("androidx.work:work-runtime-ktx:$workVersion")
    androidTestImplementation("androidx.work:work-testing:$workVersion")
    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    // Kotlin DateTime and Library Desugaring
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    // Kotlin Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    // Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
    androidTestImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    kaptAndroidTest("com.google.dagger:hilt-compiler:$hiltVersion")
    testImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    kaptTest("com.google.dagger:hilt-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-work:1.0.0")
}

kapt {
    arguments {
        arg("kapt.kotlin.generated", "$projectDir/generated/source/kaptKotlin")
        arg("room.schemaLocation", "$projectDir/schemas")
        arg("room.incremental", "true")
    }
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}