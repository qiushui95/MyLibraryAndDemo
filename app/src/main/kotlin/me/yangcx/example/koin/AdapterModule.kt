package me.yangcx.example.koin

import me.drakeet.multitype.MultiTypeAdapter
import me.yangcx.recycler.adapter.CommonAdapter
import org.koin.dsl.module.module

/**
 * 关于适配器Adapter的注入提供类
 * create by 97457
 * create at 2018/11/08 0008
 */
object AdapterModule {
    val instance = module {
        factory {
            CommonAdapter()
        }
    }
}