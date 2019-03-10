package me.yangcx.example.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteCallbackList
import android.util.Log
import me.yangcx.example.IMusicPlayer
import me.yangcx.example.IPlayerCallback
import java.util.*

/**
 *
 * create by 9745
 * create at 2019/03/09 0009
 */
class AIDLService : Service() {
    private val callbackList by lazy { RemoteCallbackList<IPlayerCallback>() }
    private var progress = 0
    private var timer: Timer? = null

    override fun onDestroy() {
        super.onDestroy()
        callbackList.kill()
    }

    private val stub = object : IMusicPlayer.Stub() {

        override fun start() {
            timer = Timer()
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    this@AIDLService.progress += 100
                    0.until(callbackList.beginBroadcast())
                        .map {
                            callbackList.getBroadcastItem(it)
                        }.forEach {
                            it.onPregressUpdate(this@AIDLService.progress)
                        }
                    callbackList.finishBroadcast()
                    Log.e("=========", this@AIDLService.progress.toString())
                }
            }, 0, 100)
        }

        override fun pause() {
            timer?.cancel()
            timer = null
        }

        override fun stop() {
            this@AIDLService.progress = 0
            timer?.cancel()
            timer = null
        }

        override fun registerCallback(callback: IPlayerCallback?) {
            callbackList.register(callback)
        }

        override fun unregisterCallback(callback: IPlayerCallback?) {
            callbackList.unregister(callback)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return stub
    }
}