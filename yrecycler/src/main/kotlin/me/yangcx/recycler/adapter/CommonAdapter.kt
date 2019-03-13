package me.yangcx.recycler.adapter

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.OneToManyFlow
import me.yangcx.recycler.binder.CommonBinder
import me.yangcx.recycler.holder.BaseHolder
import me.yangcx.recycler.sundries.DiffCallback
import me.yangcx.recycler.sundries.IEntity
import java.lang.ref.WeakReference
import kotlin.reflect.KClass

/**
 * create by 97457
 * create at 2019/03/13 0013
 */
class CommonAdapter(lifecycleOwner: LifecycleOwner) : MultiTypeAdapter() {
    private val lifecycleOwnerWeak = WeakReference(lifecycleOwner)
    override fun setItems(items: MutableList<*>) {
        val oldDealTask = Observable.just(getItems())
                .map { oldList ->
                    oldList.mapNotNull {
                        it as? IEntity
                    }
                }
        val newDealTask = Observable.just(items)
                .map { newList ->
                    newList.mapNotNull {
                        it as? IEntity
                    }.map {
                        it.copySelf()
                    }
                }
        Observable.zip(oldDealTask, newDealTask, BiFunction<List<IEntity>, List<IEntity>, Pair<List<IEntity>, DiffUtil.DiffResult>> { t1, t2 ->
            val diffResult = DiffUtil.calculateDiff(DiffCallback(t1, t2), true)
            Pair(t2, diffResult)
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwnerWeak.get()))
                .subscribe {
                    super.setItems(it.first)
                    it.second.dispatchUpdatesTo(this)
                }
    }

    inline fun <reified T : IEntity, reified VH : BaseHolder<T>> register(holderClz: KClass<VH>) {
        super.register(T::class.java, CommonBinder(holderClz))
    }

    inline fun <reified T : IEntity> register(clz: KClass<out T>): OneToManyFlow<T> {
        return super.register(clz.java)
    }
}