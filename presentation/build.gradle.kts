plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.reansen.coreprojectstructure"
    compileSdk = 34

    defaultConfig {

        applicationId = "com.reansen.coreprojectstructure"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
    }

    packaging {
        resources {
            excludes += listOf(
                "META-INF/INDEX.LIST",
                "META-INF/INDEX.LIST2",
                "META-INF/INDEX.LIST3",
                "META-INF/io.netty.versions.properties"
            )
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    //Todo RxJava
    implementation(libs.rxjava3)
    implementation(libs.activity)

    implementation("io.netty:netty-transport:4.1.75.Final") // Replace with the latest stable version
    implementation("io.netty:netty-codec:4.1.75.Final")
    implementation("io.netty:netty-handler:4.1.75.Final")
    implementation(project(":domain-layer"))


    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}