package me.yangcx.example.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.uber.autodispose.AutoDispose
import me.yangcx.base.viewmodel.AutoDisposeViewModel
import me.yangcx.example.repository.MainRepository
import me.yangcx.example.ui.TestData

/**
 * create by 97457
 * create at 2019/03/20 0020
 */
class MainViewModel(private val mainRepository: MainRepository) : AutoDisposeViewModel() {
    val dataList by lazy {
        MediatorLiveData<List<TestData>>()
    }

    fun loadDataList() {
        val liveData = mainRepository.loadDataList()
        dataList.addSource(liveData, Observer {
            dataList.removeSource(liveData)
            dataList.postValue(it)
        })
    }
}