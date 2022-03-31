package com.example.baseapp.ui.main

import android.content.Intent
import android.util.Log
import androidx.work.*
import com.example.baseapp.R
import com.example.baseapp.common.ext.requestAccessibilitiPermission
import com.example.baseapp.common.ext.requestDrawOverlayPermission
import com.example.baseapp.common.worker.ScreenWorker
import kotlinx.android.synthetic.main.activity_main.*
import vn.vano.vicall.ui.base.BaseActivity

class MainActivity : BaseActivity<MainView, MainPresenterIm>(), MainView {
    companion object {
        private const val RC_PERMISSION_STORAGE = 1
        private const val RC_PERMISSION_CONTACTS = 2
        private const val RC_PERMISSION_PHONE = 3
        private const val RC_PERMISSION_CAMERA = 4
        private const val RC_PERMISSION_CALL_LOG = 5
        private const val RC_DRAW_OVERLAY = 256
        private const val RC_BATTERY_OPTIMIZATION = 257
        private const val RC_ACCESSIBILITY = 258
    }

    override fun initView(): MainView {
        return this
    }

    override fun initPresenter(): MainPresenterIm {
        return MainPresenterIm(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initWidgets() {
        lblTest.setOnClickListener{
            requestDrawOverlayPermission(RC_DRAW_OVERLAY)
        }
        lblTurnOnAccessibility.setOnClickListener{
           requestAccessibilitiPermission(RC_ACCESSIBILITY)
        }
//        test()
    }
     val URL = "url"
    override fun test() {

        val inputData = workDataOf(
            URL to "url",
        )
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest = OneTimeWorkRequestBuilder<ScreenWorker>()
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

        val uploadWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<ScreenWorker>()
                .build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            RC_DRAW_OVERLAY -> {
                Log.e("TAG", "onActivityResult: RC_DRAW_OVERLAYRC_DRAW_OVERLAY" )
            }
        }
    }
}