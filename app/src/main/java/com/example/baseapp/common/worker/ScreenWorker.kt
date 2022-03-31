package com.example.baseapp.common.worker

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.view.View
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.baseapp.common.broadcast.ScreenReceiver
import java.text.SimpleDateFormat
import java.util.*


class ScreenWorker(
    private val ctx: Context,
    workerParameters: WorkerParameters
) :
    Worker(ctx, workerParameters) {
    var timer: Timer? = null
    var timerTask: TimerTask? = null
    override fun doWork(): Result {
//        var i = 0;
        var action = IntentFilter("screen_receiver");
        ctx.registerReceiver(ScreenReceiver(), action)
        startTimer()
        return Result.success()
    }

    //    var handler? : Handler
    fun startTimer() {
        //set a new Timer
        timer = Timer()

        //initialize the TimerTask's job
        initializeTimerTask()

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer!!.schedule(timerTask, 1000, 1000) //
    }

    fun stoptimertask(v: View?) {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
    }

    var i = 0
    fun initializeTimerTask() {
        timerTask = object : TimerTask() {
            override fun run() {
                //use a handler to run a toast that shows the current timestamp
                val calendar: Calendar = Calendar.getInstance()
                val simpleDateFormat = SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a")
                val strDate: String = simpleDateFormat.format(calendar.getTime())

                //show the toast
//                val duration = Toast.LENGTH_SHORT
//                val toast = Toast.makeText(applicationContext, strDate, duration)
                i++;
                if (i % 20 == 0) {
                    var intent = Intent("screen_receiver")
                    ctx.sendBroadcast(intent)
                    /* var intent = Intent(ctx, MainActivity::class.java)
                     ctx.startActivity(intent)*/
                }
                Log.e("TAG", "doWork: $strDate $i")
//                toast.show()
            }
        }
    }
}