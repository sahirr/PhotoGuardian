package com.photoguardian

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PhotoGuardianApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize logging, analytic stubs (if any), etc.
    }
}