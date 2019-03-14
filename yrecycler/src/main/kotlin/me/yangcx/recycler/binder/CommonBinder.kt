package me.yangcx.recycler.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import me.drakeet.multitype.ItemViewBinder
import me.yangcx.recycler.annotaion.BindHolderRes
import me.yangcx.recycler.annotaion.UnbindHolderException
import me.yangcx.recycler.holder.BaseHolder
import me.yangcx.recycler.sundries.IEntity
import me.yangcx.recycler.sundries.UnfoundConstructorException
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

/**
 * create by 97457
 * create at 2019/03/13
 */
class CommonBinder<T : IEntity, VH : BaseHolder<T>>(private val clz: KClass<VH>) : ItemViewBinder<T, VH>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH {
        val holderRes = clz.java.getAnnotation(BindHolderRes::class.java)
        if (holderRes != null && holderRes.layoutRes > 0) {
            val itemView = inflater.inflate(holderRes.layoutRes, parent, false)
            val primaryConstructor = clz.primaryConstructor
            if (primaryConstructor == null) {
                throw UnfoundConstructorException(clz.qualifiedName)
            } else {
                return primaryConstructor.call(itemView).apply {
                    this.onViewCreated(itemView)
                }
            }
        } else {
            throw UnbindHolderException()
        }
    }

    override fun onBindViewHolder(holder: VH, item: T) {
        holder.data = item
        holder.drawUi(item)
    }

    override fun onBindViewHolder(holder: VH, item: T, payloads: MutableList<Any>) {
        val list = payloads.mapNotNull {
            it as? String
        }
        if (list.isNotEmpty()) {
            holder.data = item
            holder.dataChanged(item, list)
        } else {
            onBindViewHolder(holder, item)
        }
    }
}