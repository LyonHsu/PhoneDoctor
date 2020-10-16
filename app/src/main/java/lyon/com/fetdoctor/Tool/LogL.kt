package lyon.com.fetdoctor.Tool

import android.util.Log
import lyon.com.fetdoctor.Base.AppController


object LogL {
    fun e(TAG :String, msg:String){
        if(AppController().getLDeBug())
            Log.e(TAG, msg)
    }

    fun d(TAG :String, msg:String){
        if(AppController().getLDeBug())
            Log.d(TAG, msg)
    }

    fun w(TAG :String, msg:String){
        if(AppController().getLDeBug())
            Log.w(TAG, msg)
    }

    fun i(TAG :String, msg:String){
        if(AppController().getLDeBug())
            Log.i(TAG, msg)
    }
}