package lyon.com.fetdoctor.Base

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log

import lyon.com.fetdoctor.API.VolletTool
import lyon.com.fetdoctor.Tool.LogL
import lyon.com.fetdoctor.Tool.Tool


val stockCountlimit = 15 //增加股票最多幾隻
var DeBug:Boolean=false
class AppController : Application(){
    val TAG=this::class.java.simpleName
    var appController: AppController = this

    lateinit var volletTools: VolletTool

    override fun onCreate() {
        super.onCreate()
        this.appController = this
        this.volletTools = VolletTool(this)

        val DeBug = getAppMetaDataBoolean(appController, "isDeBug", false)
        setLDeBug(DeBug)

        LogL.e(TAG,"onCreate isDeBug:"+DeBug)
    }

    @Synchronized
    fun getInstance(): AppController? {
        return this.appController
    }

    fun getVolletTool(context: Context): VolletTool {
        if(!::volletTools.isInitialized){
            volletTools = VolletTool(context)
        }
        return this.volletTools
    }




    private fun getAppMetaDataBoolean(
        context: Context,
        metaName: String,
        defaultValue: Boolean
    ): Boolean {
        try { //application标签下用getApplicationinfo，如果是activity下的用getActivityInfo
            val packageManager = context.packageManager
            val applicationInfo = packageManager.getApplicationInfo(context.packageName,PackageManager.GET_META_DATA)
            val metaData =  applicationInfo.metaData
            val value =metaData.getBoolean(metaName, defaultValue)
            if(value)
                print("isDeBug meta-data         $metaName = $value")
            return value
        } catch (e: Exception) {
            Log.e(TAG,"isDeBug:"+ Tool.FormatStackTrace(e))
            return defaultValue
        }
    }

    fun getLDeBug(): Boolean {
//        Log.e(TAG,"getLDeBug isDeBug:"+DeBug)
        return  true;// DeBug as Boolean
    }

    fun setLDeBug(dsBug:Boolean){
        DeBug=dsBug
    }
}