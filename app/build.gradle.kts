plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.jetbrains.kotlin.android)
	id("com.google.devtools.ksp")
}

android {
	namespace = "com.cold0.crier.social"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.cold0.crier.social"
		minSdk = 21
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables.useSupportLibrary = true
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
		freeCompilerArgs += listOf(
			"-Xopt-in=com.google.accompanist.pager.ExperimentalPagerApi",
			"-Xopt-in=androidx.compose.ui.ExperimentalComposeUiApi",
			"-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi"
		)
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
	// Base
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)

	// Compose
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.ui)
	implementation(libs.material3)
	implementation(libs.ui.tooling.preview)
	implementation(libs.androidx.animation)
	implementation(libs.androidx.material.icons.extended)
	implementation(libs.androidx.navigation.compose)

	// AndroidX
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(libs.androidx.material)

	// Coil Image Loader
	implementation(libs.coil.compose)

	// LiveData
	implementation(libs.androidx.runtime.livedata)
	implementation(libs.androidx.lifecycle.extensions)
	implementation(libs.androidx.lifecycle.livedata.ktx)

	// Pager
	implementation(libs.accompanist.pager)
	implementation(libs.accompanist.pager.indicators)

	// SplashScreen
	implementation(libs.androidx.core.splashscreen)

	// Dagger Hilt
	implementation(libs.hilt.android)
	implementation(libs.androidx.hilt.lifecycle.viewmodel)
	ksp(libs.hilt.compiler)

	// Room
	implementation(libs.androidx.room.runtime)
	annotationProcessor(libs.androidx.room.compiler)
	ksp(libs.androidx.room.compiler)
	implementation(libs.androidx.room.ktx)

	// Retrofit
	implementation(libs.retrofit)
	implementation(libs.converter.gson)

	// Debug Only
	debugImplementation(libs.ui.tooling)
}
