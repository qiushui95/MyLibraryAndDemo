package me.yangcx.recycler.adapter

import androidx.annotation.CheckResult
import androidx.lifecycle.LifecycleOwner
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.OneToManyFlow
import me.yangcx.recycler.binder.CommonBinder
import me.yangcx.recycler.holder.BaseHolder
import me.yangcx.recycler.sundries.AdapterDataDiffCalculateUtils
import me.yangcx.recycler.sundries.IEntity
import java.lang.ref.WeakReference
import kotlin.reflect.KClass

/**
 * create by 97457
 * create at 2019/03/13 0013
 */
class CommonAdapter : MultiTypeAdapter() {
    override fun setItems(items: MutableList<*>) {
        AdapterDataDiffCalculateUtils(this).calculateDiff(items)
    }

    internal fun setProcessedItems(items: List<IEntity>) {
        super.setItems(items)
    }

    inline fun <reified T : IEntity, reified VH : BaseHolder<T>> register(holderClz: KClass<VH>) {
        super.register(T::class.java, CommonBinder(holderClz))
    }

    @CheckResult
    inline fun <reified T : IEntity> register(clz: KClass<out T>): OneToManyFlow<T> {
        return super.register(clz.java)
    }
}