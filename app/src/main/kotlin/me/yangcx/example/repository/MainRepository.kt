package me.yangcx.example.repository

import me.yangcx.example.api.TestService

/**
 * create by 97457
 * create at 2019/03/20 0020
 */
class MainRepository(private val testService: TestService) {
    fun loadDataList() = testService.getDataList()
}