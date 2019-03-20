package me.yangcx.example.ui

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import me.yangcx.base.annotation.BindLayoutRes
import me.yangcx.example.R
import me.yangcx.example.base.BaseActivity
import me.yangcx.example.base.ViewModelActivity
import me.yangcx.example.viewmodel.MainViewModel
import me.yangcx.recycler.adapter.CommonAdapter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

@BindLayoutRes(R.layout.activity_main)
class MainActivity : ViewModelActivity() {
    private val adapter by lazy {
        CommonAdapter()
    }
    private val viewModel by viewModel<MainViewModel>()
    override fun initAfterUi() {
        EventBus.getDefault().register(this)
        rvContent.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        adapter.register(TestDataHolder::class)
        rvContent.adapter = adapter
    }

    override fun onBindViewListener() {

    }

    override fun onBindViewModel() {
        viewModel.dataList.observe(this, Observer {
            adapter.items = it
        })
        viewModel.loadDataList()
    }

    @Subscribe
    fun itemClick(data: TestData) {

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
