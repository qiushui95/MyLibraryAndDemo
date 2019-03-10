package me.yangcx.example.ui

import kotlinx.android.synthetic.main.activity_main.*
import me.yangcx.base.annotation.BindLayoutRes
import me.yangcx.example.R
import me.yangcx.example.aidl.AIDLActivity
import me.yangcx.example.base.BaseActivity

@BindLayoutRes(R.layout.activity_main)
class MainActivity : BaseActivity() {

    override fun initAfterUi() {

    }

    override fun onBindViewListener() {
        tvGoToAIDL.setOnClickListener {
            AIDLActivity.launch(this)
        }
    }
}
