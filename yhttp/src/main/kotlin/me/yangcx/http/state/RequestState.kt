package me.yangcx.http.state

/**
 * create by 97457
 * create at 2019/03/20 0020
 */
sealed class RequestState<T> {
    data class RequestError<T>(val throwable: Throwable) : RequestState<T>()
    data class ResponseError<T>(val code: Int, val message: String?) : RequestState<T>()
    class SuccessEmpty<T> : RequestState<T>()
    data class SuccessWithData<T>(val data:T) : RequestState<T>()
}