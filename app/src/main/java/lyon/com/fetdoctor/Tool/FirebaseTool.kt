//package lyon.com.fetdoctor.Tool
//
//import android.content.Context
//import android.os.Bundle
//import android.util.Log
//import com.google.firebase.analytics.FirebaseAnalytics
//
//class FirebaseTool{
//    var TAG = "FirebaseLogUtils"
//    var context: Context? = null
//    var mFirebaseAnalytics: FirebaseAnalytics? = null
//
//    constructor (context: Context) {
//        this.context = context
//        if (context == null) return
//
//
//        Log.d(TAG, "Google Firebase Analytics init")
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context)
//
//    }
//
//    /**
//     * Event: launch_app
//     * Description: 開啟App
//     */
//    fun launchApp() {
//        mFirebaseAnalytics!!.logEvent("launchApp", null)
//        Log.d(TAG, "Google Firebase Analytics setUserType launch_app:null")
//    }
//
//    fun touchMenuDialog() {
//        mFirebaseAnalytics!!.logEvent("touchMenuDialog", null)
//        Log.d(TAG, "Google Firebase Analytics setUserType touchMenuDialog:null")
//    }
//
//    fun showFloatWindow() {
//        mFirebaseAnalytics!!.logEvent("showFloatWindow", null)
//        Log.d(TAG, "Google Firebase Analytics setUserType showFloatWindow:null")
//    }
//
//    fun TouchTheADMod() {
//        mFirebaseAnalytics!!.logEvent("TouchTheADMod", null)
//        Log.d(TAG, "Google Firebase Analytics setUserType TouchTheADMod:null")
//    }
//
//    fun TheADModFail(error:Int) {
//        var errorName = "ERROR_CODE_INTERNAL_ERRO"
//        when(error){
//            0->{
//                errorName = "ERROR_CODE_INTERNAL_ERRO"
//            }
//            1->{
//                errorName = "ERROR_CODE_INVALID_REQUEST"
//            }
//            2->{
//                errorName = "ERROR_CODE_NETWORK_ERROR"
//            }
//            3->{
//                errorName = "ERROR_CODE_NO_FILL"
//            }
//        }
//
//        var bundle = Bundle();
//        bundle.putString(FirebaseAnalytics.Param.VALUE, error.toString());
//        bundle.putString(FirebaseAnalytics.Param.CONTENT, errorName);
//        mFirebaseAnalytics!!.logEvent("TouchTheADMod", bundle)
//        Log.d(TAG, "Google Firebase Analytics setUserType TouchTheADMod:null")
//    }
//
//    fun addStock(name:String,id:String, starMode:String){
//        var bundle = Bundle();
//        bundle.putString("name", name);
//        bundle.putString("id", id);
//        bundle.putString("type", starMode);
//        mFirebaseAnalytics!!.logEvent("AddStockName", bundle)
//        Log.d(TAG, "Google Firebase Analytics setUserType AddStockName:"+name+" :"+id+" type:"+starMode)
//    }
//
//    fun checkVolletToolError(error:String){
//        var bundle = Bundle();
//        bundle.putString("name", error);
//
//        mFirebaseAnalytics!!.logEvent("volletToolError", bundle)
//        Log.d(TAG, "Google Firebase Analytics volletToolError:"+error)
//    }
//}