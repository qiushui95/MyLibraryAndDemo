//package me.yangcx.recycler.holder
//
//import android.view.View
//import androidx.recyclerview.widget.BindAwareViewHolder
//import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
//import com.uber.autodispose.lifecycle.LifecycleEndedException
//import com.uber.autodispose.lifecycle.LifecycleScopeProvider
//import io.reactivex.Observable
//import io.reactivex.subjects.BehaviorSubject
//
//abstract class AutoDisposeViewHolder(itemView: View) : BindAwareViewHolder(itemView), LifecycleScopeProvider<ViewHolderEvent> {
//    companion object {
//        private val CORRESPONDING_EVENTS = CorrespondingEventsFunction<ViewHolderEvent> {
//            when (it) {
//                ViewHolderEvent.BIND -> {
//                    ViewHolderEvent.UNBIND
//                }
//                else -> {
//                    throw LifecycleEndedException("Cannot use ViewHolder lifecycle after unbind.")
//                }
//            }
//        }
//    }
//
//    private val lifecycleEvents by lazy {
//        BehaviorSubject.create<ViewHolderEvent>()
//    }
//
//    override fun lifecycle(): Observable<ViewHolderEvent> {
//        return lifecycleEvents.hide()
//    }
//
//    override fun correspondingEvents(): CorrespondingEventsFunction<ViewHolderEvent> {
//        return CORRESPONDING_EVENTS
//    }
//
//    override fun peekLifecycle(): ViewHolderEvent? {
//        return lifecycleEvents.value
//    }
//
//    override fun onBind() {
//        lifecycleEvents.onNext(ViewHolderEvent.BIND)
//    }
//
//    override fun onUnbind() {
//        lifecycleEvents.onNext(ViewHolderEvent.UNBIND)
//    }
//}