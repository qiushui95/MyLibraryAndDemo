package me.yangcx.example.koin

import me.yangcx.example.api.TestService
import org.koin.dsl.module.module
import retrofit2.Retrofit

/**
 * 关于请求接口的注入提供类
 * create by 97457
 * create at 2018/11/08 0008
 */
object ServiceModule {
    val instance = module {
        single {
            get<Retrofit>().create(TestService::class.java)
        }
    }
}