package me.yangcx.example.sundries

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.squareup.leakcanary.LeakCanary
import me.yangcx.example.koin.*
import org.koin.android.ext.android.startKoin

/**
 * create by 97457
 * create at 2019/03/20 0020
 */
class UsefulApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        startKoin(
            this, listOf(
                AdapterModule.instance,
                FileModule.instance,
                JsonModule.instance,
                RemoteModule.instance,
                RepositoryModule.instance,
                ServiceModule.instance,
                ViewModelModule.instance
            )
        )
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}