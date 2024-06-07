/*val implementation: Unit = Unit

val annotationProcessor: Unit = Unit

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.gms.google-services")


}

android {


    namespace = "com.example.anew"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.anew"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding= true

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







    //noinspection UseTomlInstead
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    //noinspection UseTomlInstead
    implementation("com.github.bumptech.glide:glide:4.12.0")
    //noinspection UseTomlInstead
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    // Import the BoM for the Firebase platform
    //noinspection GradleDependency,BomWithoutPlatform,UseTomlInstead
    implementation("com.google.firebase:firebase-bom:32.8.0")



    //noinspection UseTomlInstead

    //noinspection UseTomlInstead,GradleDependency
    implementation ("com.google.android.gms:play-services-auth:19.0.0")
    //noinspection UseTomlInstead
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.database)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //noinspection UseTomlInstead
    implementation ("com.google.firebase:firebase:21.1.0")
    //noinspection GradleDependency,UseTomlInstead
    implementation ("com.google.firebase:firebase-database:20.0.0")
}
*/

val implementation: Unit = Unit

val annotationProcessor: Unit = Unit

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.gms.google-services")

}

android {


    namespace = "com.example.anew"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.anew"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding= true

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



    //noinspection UseTomlInstead
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    //noinspection UseTomlInstead
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation(libs.firebase.auth)
    //noinspection UseTomlInstead
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    // Import the BoM for the Firebase platform
    //noinspection GradleDependency,BomWithoutPlatform,UseTomlInstead
    implementation("com.google.firebase:firebase-bom:32.8.0")



    //noinspection UseTomlInstead

    //noinspection UseTomlInstead,GradleDependency
    implementation ("com.google.android.gms:play-services-auth:19.0.0")
    //noinspection UseTomlInstead
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.database)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}