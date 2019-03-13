package me.yangcx.example.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import me.yangcx.base.annotation.BindLayoutRes
import me.yangcx.example.R
import me.yangcx.example.base.BaseActivity
import me.yangcx.recycler.adapter.CommonAdapter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import kotlin.random.Random

@BindLayoutRes(R.layout.activity_main)
class MainActivity : BaseActivity() {
    private val adapter by lazy {
        CommonAdapter(this)
    }
    private val list by lazy {
        0.until(100)
            .map {
                TestData(it + 1, Random.nextInt(100) + 1)
            }.toMutableList()
    }

    override fun initAfterUi() {
        EventBus.getDefault().register(this)
        rvContent.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        adapter.register(TestDataHolder::class)
        rvContent.adapter = adapter
        adapter.items = list
    }

    override fun onBindViewListener() {

    }

    @Subscribe
    fun itemClick(data: TestData) {
        val testData = list.first {
            it.isItemSame(data)
        }
        testData.data--
        if (testData.data <= 0) {
            list.remove(testData)
        }
        adapter.items = list
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
