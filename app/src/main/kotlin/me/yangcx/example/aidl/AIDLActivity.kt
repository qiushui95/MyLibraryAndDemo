package me.yangcx.example.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import kotlinx.android.synthetic.main.activity_aidl.*
import me.yangcx.base.annotation.BindLayoutRes
import me.yangcx.example.IMusicPlayer
import me.yangcx.example.IPlayerCallback
import me.yangcx.example.R
import me.yangcx.example.base.BaseActivity
import org.jetbrains.anko.toast

/**
 * 通过AIDL方式通信的Activity
 * create by 9745
 * create at 2019/03/09 0009
 */
@BindLayoutRes(R.layout.activity_aidl)
class AIDLActivity : BaseActivity() {
    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, AIDLActivity::class.java))
        }
    }

    private var service: IMusicPlayer? = null
    private val callback by lazy {
        object : IPlayerCallback.Stub() {
            override fun onPregressUpdate(progress: Int) {
                Log.e("=========", progress.toString())
                tvProgress.post {
                    tvProgress.text = "已播放${progress}ms"
                }
            }
        }
    }
    private val connection by lazy {
        object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {
                toast("已断开连接")
                service?.unregisterCallback(callback)
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                toast("已连接")
                this@AIDLActivity.service = IMusicPlayer.Stub.asInterface(service)
                this@AIDLActivity.service?.registerCallback(callback)
            }
        }
    }

    override fun initAfterUi() {

    }

    override fun onBindViewListener() {
        tvBind.setOnClickListener {
            val intent = Intent(this, AIDLService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        tvStart.setOnClickListener {
            service?.start()
        }
        tvPause.setOnClickListener {
            service?.pause()
        }
        tvStop.setOnClickListener {
            service?.stop()
        }
        tvUnbind.setOnClickListener {
            unbindService(connection)
        }
    }
}