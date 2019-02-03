package me.yangcx.example.base

import android.os.Bundle
import com.noober.background.BackgroundLibrary
import me.yangcx.base.ui.FoundationActivity

/**
 * 基础Activity
 * create by 97457
 * create at 2018/12/06 0006
 */
abstract class BaseActivity : FoundationActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		BackgroundLibrary.inject(this)
		super.onCreate(savedInstanceState)
	}
}