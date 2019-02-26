package me.yangcx.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * 绑定生命周期的ViewModel
 * create by 9745
 * create at 2019/02/04 0004
 */
class AutoDisposeAndroidViewModel(application: Application) : AndroidViewModel(application), LifecycleScopeProvider<ViewModelEvent> {

    companion object {
        private val CORRESPONDING_EVENTS = CorrespondingEventsFunction<ViewModelEvent> {
            ViewModelEvent.CLEARED
        }
    }

    private val lifecycleEvents by lazy {
        BehaviorSubject.createDefault(ViewModelEvent.CREATED)
    }

    override fun lifecycle(): Observable<ViewModelEvent> {
        return lifecycleEvents.hide()
    }

    override fun correspondingEvents(): CorrespondingEventsFunction<ViewModelEvent> {
        return CORRESPONDING_EVENTS
    }

    override fun peekLifecycle(): ViewModelEvent? {
        return lifecycleEvents.value
    }

    override fun onCleared() {
        lifecycleEvents.onNext(ViewModelEvent.CLEARED)
        super.onCleared()
    }
}