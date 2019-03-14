package me.yangcx.recycler.extend

import me.drakeet.multitype.OneToManyEndpoint
import me.drakeet.multitype.OneToManyFlow
import me.yangcx.recycler.binder.CommonBinder
import me.yangcx.recycler.holder.BaseHolder
import me.yangcx.recycler.sundries.IEntity
import kotlin.reflect.KClass

inline fun <reified T : IEntity, reified VH : BaseHolder<T>> OneToManyFlow<T>.to(vararg holderClz: KClass<VH>): OneToManyEndpoint<T> {
    val binderList = holderClz.map {
        CommonBinder(it)
    }
    return to(*Array(binderList.size) {
        binderList[it]
    })
}