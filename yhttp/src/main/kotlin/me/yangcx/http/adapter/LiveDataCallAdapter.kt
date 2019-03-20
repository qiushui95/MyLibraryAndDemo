package me.yangcx.http.adapter

import androidx.lifecycle.LiveData
import me.yangcx.http.state.RequestState
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

/**
 *
 * create by 97457
 * create at 2019/03/20 0020
 */
class LiveDataCallAdapter<T : Any>(private val responseType: Type) : CallAdapter<T, LiveData<RequestState<T>>> {
    override fun adapt(call: Call<T>) = object : LiveData<RequestState<T>>() {
        private var isSuccess = false
        override fun onActive() {
            super.onActive()
            if (!isSuccess) {
                enqueue()
            }
        }

        override fun onInactive() {
            super.onInactive()
            dequeue()
        }

        private fun enqueue() {
            call.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    postValue(RequestState.RequestError(t))
                }

                @Suppress("UNCHECKED_CAST")
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    isSuccess = true
                    val body = response.body()
                    if (response.isSuccessful && body is T) {
                        postValue(RequestState.SuccessWithData<T>(body))
                    } else if (body == null || response.code() == 204) {
                        postValue(RequestState.SuccessEmpty())
                    } else {
                        val errorBody = response.errorBody()
                        val errorMessage = errorBody?.string() ?: "Unknown Error"
                        errorBody?.close()
                        postValue(RequestState.ResponseError(response.code(), errorMessage))
                    }
                }
            })
        }

        private fun dequeue() {
            if (call.isExecuted) call.cancel()
        }

    }

    override fun responseType() = responseType
}