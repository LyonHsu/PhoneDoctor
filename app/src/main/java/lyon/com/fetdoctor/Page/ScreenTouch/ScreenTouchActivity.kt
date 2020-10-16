package lyon.com.fetdoctor.Page.ScreenTouch

import android.app.ActionBar
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.screen_touch_activity.*
import lyon.com.fetdoctor.Base.BaseActivity
import lyon.com.fetdoctor.R

val spCount = 6
class ScreenTouchActivity :BaseActivity(){
    val TAG = this::class.java.simpleName;
    lateinit var context: Context
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var time:TextView
    lateinit var adapter: ScreenTouchAdapter
    var timeDown:Int = 60

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }else{
            val decorView = window.decorView
// Hide the status bar.
// Hide the status bar.
            val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
            decorView.systemUiVisibility = uiOptions
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
            val actionBar: ActionBar? = actionBar
            actionBar?.hide()
        }
        context = this
        setContentView(R.layout.screen_touch_activity)
        gridLayoutManager = GridLayoutManager(this,spCount)
        recycleView.layoutManager= gridLayoutManager //預設
        adapter = object:ScreenTouchAdapter(this){
            override fun buttonClear() {
                super.buttonClear()
                Toast.makeText(context,"觸控沒問題！！",Toast.LENGTH_LONG).show()
                finish()
            }
        }
        recycleView.adapter = adapter


        time = findViewById(R.id.time)
        callRunnable()

    }

    val handler = Handler()
    fun callRunnable(){
        handler.removeCallbacks(timeDownRunnable)
        handler.postDelayed(timeDownRunnable,(1000).toLong())
    }
    var timeDownRunnable = object :Runnable {
        override fun run() {
            timeDown--
            time.setText(timeDown.toString())
            Log.d(TAG,"size tourch():"+timeDown)
            if(timeDown<=0){
                val i= adapter.getTouchSize()
                Toast.makeText(context,"有"+i+"個觸控有問題！！",Toast.LENGTH_LONG).show()
                time.isClickable=true

            }else{
                callRunnable()
            }
        }
    }
}