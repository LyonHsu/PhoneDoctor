package lyon.com.fetdoctor.Tool

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import lyon.com.fetdoctor.R

import java.io.IOException
import java.io.PrintWriter
import java.io.StringWriter
import java.io.Writer
import java.util.*


object Tool{
    val TAG= this::class.java.simpleName

    fun getVersionCode(context:Context):Int{
        var packageManager:PackageManager = context.packageManager
        var packageInfo = packageManager.getPackageInfo(context.packageName,0)
        var versionCode=packageInfo.versionCode
        return versionCode
    }

    fun getVersionName(context:Context):String{
        var versionName:String

            var packageManager: PackageManager = context.packageManager
            var packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            versionName = packageInfo.versionName

        return versionName
    }

    fun dpToPx(context: Context,dp:Int):Int{
        if(context == null) {
            return -1;
        }
        val scale = context.resources.displayMetrics.density
        var px =  ((dp * scale + 0.5f).toInt())
        LogL.d(TAG,"dpToPx:"+px)
        return px

    }

    fun isConnect(context: Context):Boolean{
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
        return isConnected
    }

    fun FormatStackTrace(throwable: Throwable?): String? {
        if (throwable == null) return ""
        var rtn = throwable.stackTrace.toString()
        try {
            val writer: Writer = StringWriter()
            val printWriter = PrintWriter(writer)
            throwable.printStackTrace(printWriter)
            printWriter.flush()
            writer.flush()
            rtn = writer.toString()
            printWriter.close()
            writer.close()
        } catch (e: IOException) {
            Log.e(TAG,"FormatStackTrace "+e)
        } catch (ex: Exception) {
            Log.e(TAG,"FormatStackTrace "+ex)
        }
        return rtn
    }

    fun shareEmail(
        context: Context,
        to_email_id: String,
        subject: String?,
        body: String?
    ) {
        // This function will open the email client installed in the device to share from your own app through intent.
        val sharingIntent = Intent(Intent.ACTION_SEND, Uri.parse(""))
        sharingIntent.type = "message/rfc822"

        /* All the below fields are optional. If not given simply opens the email client */
        // To email id
        sharingIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to_email_id))
        // Subject that needs to appear while sharing
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        // Body of the mail content shared.
        sharingIntent.putExtra(Intent.EXTRA_TEXT, body)
        context.startActivity(
            Intent.createChooser(sharingIntent, "Share content through email")
        )
    } // shareEmail

    fun hideKeyboard(context: Context,view: View) {
        val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}