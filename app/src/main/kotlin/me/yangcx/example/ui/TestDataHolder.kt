package me.yangcx.example.ui

import android.view.View
import kotlinx.android.synthetic.main.item_test.view.*
import me.yangcx.example.R
import me.yangcx.recycler.annotaion.BindHolderRes
import me.yangcx.recycler.holder.BaseHolder
import org.greenrobot.eventbus.EventBus

@BindHolderRes(R.layout.item_test)
class TestDataHolder(itemView: View) : BaseHolder<TestData>(itemView) {
    override fun onViewCreated(itemView: View) {
        itemView.setOnClickListener {
            EventBus.getDefault().post(data)
        }
    }

    override fun drawUi(data: TestData) {
        itemView.tvTest.text = data.toString()
    }

    override fun dataChanged(data: TestData, changeList: List<String>) {
        itemView.tvTest.text = data.toString()
    }
}