package lyon.com.fetdoctor.Object


import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import lyon.com.fetdoctor.R
import lyon.com.fetdoctor.Tool.LogL
import lyon.com.fetdoctor.Tool.Tool


abstract class TouchButton : AppCompatImageView {
    constructor(context: Context?) : super(context!!) {init(context)}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {init(context)}
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!,
        attrs,
        defStyle
    ) {
        init(context)
    }
    val TAG = this::class.java.simpleName;
    private var isVisibleView = true
    fun init(context: Context){

        layoutParams = LinearLayout.LayoutParams(getW(), getH())


        setImageDrawable(context.resources.getDrawable(R.drawable.add))
        setBackgroundDrawable(context.resources.getDrawable(R.drawable.btn_blue))
//        setOnClickListener(){
//            tourch()
//        }
       setOnTouchListener { view, motionEvent -> when (motionEvent.action){
               MotionEvent.ACTION_DOWN -> {

                   tourch()
               }
            }
           true
       }
    }

    fun tourch(){
        Log.d(TAG,"size tourch()")
        if(isVisible) {
            val animation2: Animation = AnimationUtils.loadAnimation(
                context,
                R.anim.clockwise
            )
            startAnimation(animation2)
            visibility = View.INVISIBLE
            if (isVisibleView) {
                isVisibleView = false
                viewHide()
            }

        }
    }

    fun getW():Int{
        val diaplay = context.resources.displayMetrics
        val w = diaplay.widthPixels
        val h = diaplay.heightPixels
        LogL.d(TAG,"size:"+ w/6)
        return  w/6
    }

    fun getH():Int{
        val diaplay = context.resources.displayMetrics
        val w = diaplay.widthPixels
        val h = diaplay.heightPixels
        LogL.d(TAG,"size:"+ h/10)
        return h/10

    }
    abstract fun viewHide()

}