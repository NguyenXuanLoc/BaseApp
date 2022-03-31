package com.example.baseapp.common

import android.app.Application
import com.example.baseapp.common.util.SharedPreferencesUtil
import com.example.baseapp.common.util.TimberReleaseTree
import com.facebook.drawee.backends.pipeline.BuildConfig
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import org.jetbrains.anko.ctx
import timber.log.Timber


class MyApplication : Application() {
    val sharedPref by lazy { SharedPreferencesUtil.customPrefs(ctx) }

    companion object {
        lateinit var instance: MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initFresco()
        initTimber()
    }

    // Facebook fresco - for loading image
    private fun initFresco() {
        ImagePipelineConfig.newBuilder(this).apply {
            isDownsampleEnabled = true
        }
            .build()
            .run {
                Fresco.initialize(this@MyApplication, this)
            }
    }

    // Timber - for logging
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(TimberReleaseTree())
        }
    }
}