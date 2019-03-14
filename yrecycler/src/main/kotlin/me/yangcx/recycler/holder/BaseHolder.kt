package me.yangcx.recycler.holder

import android.view.View
import me.yangcx.recycler.sundries.IEntity

abstract class BaseHolder<T : IEntity>(itemView: View) : AutoDisposeViewHolder(itemView) {
    lateinit var data: T

    /**
     * 绘制item界面
     */
    abstract fun drawUi(data: T)

    /**
     * 数据局部变化、重绘局部界面
     */
    open fun dataChanged(data: T, changeList: List<String>) {}

    /**
     * View创建之后的回调
     */
    open fun onViewCreated(itemView: View) {}
}