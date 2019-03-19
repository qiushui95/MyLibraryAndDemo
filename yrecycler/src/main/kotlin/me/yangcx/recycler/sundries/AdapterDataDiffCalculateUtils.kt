package me.yangcx.recycler.sundries

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import me.drakeet.multitype.MultiTypeAdapter
import me.yangcx.recycler.adapter.CommonAdapter
import java.lang.ref.WeakReference

/**
 * create by 97457
 * create at 2019/03/19 0019
 */
internal class AdapterDataDiffCalculateUtils(adapter: CommonAdapter) {
    private val adapterWeakRef = WeakReference(adapter)
    fun calculateDiff(newList: List<*>) {
        val oldDealTask = GlobalScope.async(Dispatchers.IO) {
            val oldList = adapterWeakRef.get()?.items ?: mutableListOf<Any?>()
            oldList.mapNotNull {
                it as? IEntity
            }
        }
        val newDealTask = GlobalScope.async(Dispatchers.IO) {
            newList.mapNotNull {
                it as? IEntity
            }.map {
                it.copySelf()
            }
        }
        GlobalScope.launch(Dispatchers.IO) {
            val oldProcessedList = oldDealTask.await()
            val newProcessedList = newDealTask.await()
            launch(Dispatchers.Main) {
                adapterWeakRef.get()?.setProcessedItems(newProcessedList)
            }.join()
            val diffResult = DiffUtil.calculateDiff(DiffCallback(oldProcessedList, newProcessedList), true)
            launch(Dispatchers.Main) {
                val adapter = adapterWeakRef.get()
                if (adapter != null) {
                    diffResult.dispatchUpdatesTo(adapter)
                }
            }
        }
    }
}