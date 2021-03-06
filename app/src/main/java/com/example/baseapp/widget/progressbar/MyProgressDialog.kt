package com.example.baseapp.widget.progressbar

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.example.baseapp.R


class MyProgressDialog(ctx: Context) : Dialog(ctx) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawableResource(R.drawable.bg_black_transparent)
        setContentView(R.layout.layout_progressbar)
    }
}
