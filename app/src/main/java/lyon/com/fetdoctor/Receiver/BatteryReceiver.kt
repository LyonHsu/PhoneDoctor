package lyon.com.fetdoctor.Receiver

import android.app.ApplicationErrorReport
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.Toast
import lyon.com.fetdoctor.Tool.LogL

abstract class BatteryReceiver : BroadcastReceiver() {
    val TAG =  this::class.java.simpleName
    override fun onReceive(context: Context?, intent: Intent) {
        val voltage: Int = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0)
//        Toast.makeText(context,"總電量: " + voltage + "%"
//               ,Toast.LENGTH_SHORT).show();
//        onBatteryReceiver(context,intent)
    }

//    abstract fun onBatteryReceiver(context: Context?, intent: Intent)
}