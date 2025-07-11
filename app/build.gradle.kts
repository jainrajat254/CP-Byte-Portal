plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}


android {
    namespace = "com.example.cpbyte_portal"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.cpbyte_portal"
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

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
    stabilityConfigurationFile = rootProject.layout.projectDirectory.file("stability_config.conf")
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
    implementation(libs.transport.api)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("androidx.compose.material:material-icons-extended:1.6.0")

    //icons
    implementation("androidx.compose.material:material-icons-extended")

    //koin(dependency injection)
    implementation ("io.insert-koin:koin-androidx-compose:3.5.3")
    implementation ("io.insert-koin:koin-android:3.5.3")
    implementation("io.insert-koin:koin-androidx-compose:1.1.2")

    //ktor(client connection)
    implementation(libs.ktor.client.okhttp)
    implementation ("io.ktor:ktor-client-content-negotiation:3.1.3")
    implementation ("io.ktor:ktor-serialization-kotlinx-json:3.1.3")
    implementation("io.ktor:ktor-client-cio:3.1.3")
    implementation ("io.ktor:ktor-client-android:3.1.3") // for Android client
    implementation ("io.ktor:ktor-client-logging:3.1.3") // for logging plugin
    implementation ("io.ktor:ktor-client-core:3.1.3") // for base functionality
    implementation("io.ktor:ktor-client-auth:3.1.3")
    implementation(libs.material3.adaptive)

    //Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.1")
    //navigation
    implementation("androidx.navigation:navigation-compose:2.8.9")

    //coil(images)
    implementation("io.coil-kt.coil3:coil-compose:3.1.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.1.0")

    implementation("com.airbnb.android:lottie-compose:6.6.7")

    //serialization
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // MPAndroidChart for charts
    implementation ("com.github.PhilJay:MPAndroidChart:3.1.0")

}