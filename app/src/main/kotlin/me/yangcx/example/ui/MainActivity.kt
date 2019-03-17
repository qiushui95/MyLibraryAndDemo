package me.yangcx.example.ui

import android.util.Log
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.transition.TransitionManager
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import kotlinx.android.synthetic.main.activity_main.*
import me.yangcx.base.annotation.BindLayoutRes
import me.yangcx.example.R
import me.yangcx.example.base.BaseActivity

@BindLayoutRes(R.layout.activity_main)
class MainActivity : BaseActivity() {
    private val bottomShowConstraintSet by lazy {
        ConstraintSet().apply {
            clone(clContainer)
        }
    }
    private val bottomHideConstraintSet by lazy {
        ConstraintSet().apply {
            clone(clContainer)
            clear(bnvBottom.id)
            connect(bnvBottom.id, ConstraintSet.START, clContainer.id, ConstraintSet.START)
            connect(bnvBottom.id, ConstraintSet.END, clContainer.id, ConstraintSet.END)
            connect(bnvBottom.id, ConstraintSet.TOP, clContainer.id, ConstraintSet.BOTTOM)
        }
    }

    override fun initAfterUi() {
        val navController = findNavController(R.id.fragmentContainer)
        bnvBottom.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.moreFragment) {
                changeBottomVisible(false)
            } else {
                changeBottomVisible(true)
            }
        }
        bnvBottom.isItemHorizontalTranslationEnabled=false
        bnvBottom.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
    }

    override fun onBindViewListener() {

    }

    private fun changeBottomVisible(isVisible: Boolean) {
        TransitionManager.beginDelayedTransition(clContainer)
        if (isVisible) {
            bottomShowConstraintSet.applyTo(clContainer)
        } else {
            bottomHideConstraintSet.applyTo(clContainer)
        }
    }
}
