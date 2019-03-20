package me.yangcx.example.koin

import android.app.Application
import org.koin.dsl.module.module

/**
 * 关于Application的注入提供类
 * create by 9745
 * create at 2018/11/09 0009
 */
class ApplicationModule(private val application: Application) {
    val instance = module {
        single {
            application
        }
    }
}