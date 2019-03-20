package me.yangcx.example.koin

import android.app.Application
import me.yangcx.base.utils.DirectoryUtils
import me.yangcx.example.sundries.Constants
import org.koin.dsl.module.module

/**
 * 关于文件的注入提供类
 * create by 97457
 * create at 2018/11/08 0008
 */
object FileModule {
    val instance = module {
        single(name = Constants.NAME_CACHE_ROOT_DIR) {
            DirectoryUtils.getRootCache(get<Application>())
        }
    }
}