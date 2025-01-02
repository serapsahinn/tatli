plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.tatli"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tatli"
        minSdk = 29
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

    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.core:core-ktx:1.6.0")


    implementation ("com.google.firebase:firebase-database:20.3.0")// Realtime Database için
    implementation ("com.google.firebase:firebase-firestore:24.0.0") // Firestore için


    // Room Bağımlılıkları
    implementation("androidx.room:room-runtime:2.4.3")
    implementation(libs.firebase.database)
    //implementation(libs.firebase.firestore) // Room
    annotationProcessor("androidx.room:room-compiler:2.4.3") // Room için
    implementation("androidx.room:room-ktx:2.4.2") // Room KTX (isteğe bağlı)

//resim için
    implementation("com.squareup.picasso:picasso:2.8")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}