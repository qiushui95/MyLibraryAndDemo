package me.yangcx.http.adapter

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * create by 97457
 * create at 2019/03/20 0020
 */
class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val observableType = CallAdapter.Factory.getParameterUpperBound(0, returnType as ParameterizedType) as? ParameterizedType
                ?: throw IllegalArgumentException("resource must be parameterized")
        val type = CallAdapter.Factory.getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter<Any>(type)
    }
}