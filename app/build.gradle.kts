plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
}

android {
    namespace = "de.daywalk3r666.forms"
    compileSdk = 33
    buildToolsVersion = "33.0.0"

    defaultConfig {
        applicationId = "de.daywalk3r666.forms"
        minSdk = 1
        targetSdk = 33
        versionCode = 10
        versionName = "1.0"
        resConfigs = "en"
            
signingConfigs {
        create("gh-actions") {
            storeFile = file("${System.getenv("RUNNER_TEMP")}/keystore/keystore.jks")
            storePassword = System.getenv("KEYSTORE_PASSWORD")
            keyAlias = System.getenv("SIGNING_KEY_ALIAS")
            keyPassword = System.getenv("SIGNING_KEY_PASSWORD")
        }
    } 

    }
    
       applicationVariants.all {
        outputs.all {
            (this as? ApkVariantOutputImpl)?.outputFileName =
                "Forms_${versionName}_${buildType.name}.apk"
        }
    }
    
    buildTypes {
        debug {
            isMinifyEnabled = false
            IsShrinkResources = false
            isCrunchPngs = false
            signingConfig = signingConfigs.findByName("gh-actions")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isCrunchPngs = true
            signingConfig = signingConfigs.findByName("gh-actions")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    lintOptions {
        SetJavaScriptEnabled = false
    }

}
