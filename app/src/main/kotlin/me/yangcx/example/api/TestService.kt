package me.yangcx.example.api

import androidx.lifecycle.LiveData
import me.yangcx.example.ui.TestData
import retrofit2.http.GET

/**
 * create by 97457
 * create at 2019/03/20 0020
 */
interface TestService {
    @GET("5hH3sYfb6c6ae86fc0cd75b6afab3f7023c37b6f55614c9?uri=api/getDataList")
    fun getDataList(): LiveData<List<TestData>>
}