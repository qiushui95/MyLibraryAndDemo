package me.yangcx.recycler.annotaion

import androidx.annotation.LayoutRes
import java.lang.annotation.Inherited

/**
 * create by 97457
 * create at 2019/03/13
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
annotation class BindHolderRes(@LayoutRes val layoutRes: Int)