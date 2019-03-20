package me.yangcx.example.koin

import me.yangcx.example.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.create

/**
 * 关于ViewModel的注入提供类
 * create by 97457
 * create at 2018/11/08
 */
object ViewModelModule {
    val instance = module {
        viewModel {
            create<MainViewModel>()
        }
    }
}