package me.yangcx.example.ui

import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_common.*
import me.yangcx.base.annotation.BindLayoutRes
import me.yangcx.example.R
import me.yangcx.example.base.BaseFragment

/**
 *
 * create by 97457
 * create at 2019/03/17 0017
 */
@BindLayoutRes(R.layout.fragment_common)
class HomeFragment : BaseFragment() {
    override fun initThis(rootView: View) {
        tvContent.text = "点我去下一页"
    }

    override fun bindViewListener(rootView: View) {
        tvContent.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toMoreFragment())
        }
    }
}