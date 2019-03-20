package me.yangcx.example.koin

import android.app.Application
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import me.yangcx.example.sundries.Constants
import me.yangcx.http.adapter.LiveDataCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import com.readystatesoftware.chuck.ChuckInterceptor

/**
 * 关于网络请求的注入提供类
 * create by 97457
 * create at 2018/11/08 0008
 */
object RemoteModule {
    val instance = module {
        single {
            Cache(File(get<File>(Constants.NAME_CACHE_ROOT_DIR), "http"), 100 * 1024 * 1024)
        }
        single {
            ChuckInterceptor(get<Application>())
        }
        single<CallAdapter.Factory> {
            LiveDataCallAdapterFactory()
        }
        single<Converter.Factory> {
            GsonConverterFactory.create(get())
        }
        single {
            OkHttpClient.Builder()
                    .cache(get())
                    .connectTimeout(10 * 1000, TimeUnit.MILLISECONDS)
                    .readTimeout(5 * 1000, TimeUnit.MILLISECONDS)
                    .writeTimeout(5 * 1000, TimeUnit.MILLISECONDS)
                    .addInterceptor(get<ChuckInterceptor>())
                    .build()
        }
        single {
            Retrofit.Builder()
                    .baseUrl("http://mock.eolinker.com/")
                    .addConverterFactory(get())
                    .addCallAdapterFactory(get())
                    .client(get())
                    .build()
        }
    }
}