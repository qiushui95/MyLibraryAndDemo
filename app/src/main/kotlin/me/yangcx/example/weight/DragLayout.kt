package me.yangcx.example.weight

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.customview.widget.ViewDragHelper

/**
 * 可拖动子控件的ViewGroup
 * create by 9745
 * create at 2019/03/09 0009
 */
class DragLayout(context: Context, attrs: AttributeSet?, defStyleRes: Int) : ConstraintLayout(context, attrs, defStyleRes) {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private var autoBackOriginLeft = 0
    private var autoBackOriginTop = 0

    private val dragHelper by lazy {
        ViewDragHelper.create(this, 1f, object : ViewDragHelper.Callback() {
            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return child is TextView
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                return when {
                    left < 0 -> 0
                    left + child.width > width -> width - child.width
                    else -> left
                }
            }

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                return when {
                    top < 0 -> 0
                    top + child.height > height -> height - child.height
                    else -> top
                }
            }

            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                Log.e("====",releasedChild.x.toString().plus("---").plus(releasedChild.left))
                if (isAutoBackView(releasedChild)) {
                    backToOrigin(releasedChild)
                }
            }
        })
    }

    private fun backToOrigin(releasedChild: View) {
        dragHelper.settleCapturedViewAt(autoBackOriginLeft, autoBackOriginTop)
        invalidate()
    }

    override fun computeScroll() {
        super.computeScroll()
        if (dragHelper.continueSettling(true)) {
            invalidate()
        }
    }

    private fun isAutoBackView(child: View): Boolean {
        return indexOfChild(child) == childCount - 1
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val autoBackView = getChildAt(childCount - 1)
        autoBackOriginLeft = autoBackView.left
        autoBackOriginTop = autoBackView.top
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return dragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragHelper.processTouchEvent(event)
        return true
    }
}