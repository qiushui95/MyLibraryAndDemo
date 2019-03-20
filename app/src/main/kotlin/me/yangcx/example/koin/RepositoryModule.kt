package me.yangcx.example.koin

import me.yangcx.example.repository.MainRepository
import org.koin.dsl.module.module
import org.koin.experimental.builder.create

/**
 * 关于Repository的注入提供类
 * create by 97457
 * create at 2018/11/08 0008
 */
object RepositoryModule {
    val instance = module {
        single {
            create<MainRepository>()
        }
    }
}