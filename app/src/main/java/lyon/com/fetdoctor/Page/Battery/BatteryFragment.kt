package lyon.com.fetdoctor.Page.Battery

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import lyon.com.fetdoctor.Page.MainPagerAdapter
import lyon.com.fetdoctor.R
import lyon.com.fetdoctor.Receiver.BatteryReceiver
import lyon.com.fetdoctor.Tool.BatteryInfo

/**
 * 廣播監聽獲取到電池的資訊
 */

class BatteryFragment : Fragment() {
    val TAG = this::class.java.simpleName
    private lateinit var pagerAdapter: MainPagerAdapter
    lateinit var batteryVoltage: TextView
    private lateinit var batteryTemperature: TextView
    private lateinit var batteryCurrentCapacity: TextView
    private lateinit var batteryTotalCapacity: TextView
    private lateinit var batteryBatteryStatus: TextView
    private lateinit var batteryChargingStatus: TextView
    private lateinit var batteryHealthStatus: TextView
    private lateinit var batteryBatteryTechnology: TextView
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.battery_fragment, container, false)
        batteryVoltage  = view.findViewById(R.id.batteryVoltage)
        batteryTemperature  = view.findViewById(R.id.batteryTemperature)
        batteryCurrentCapacity = view.findViewById(R.id.batteryCurrentCapacity)
        batteryTotalCapacity = view.findViewById(R.id.batteryTotalCapacity)
        batteryBatteryStatus = view.findViewById(R.id.batteryBatteryStatus)
        batteryChargingStatus = view.findViewById(R.id.batteryChargingStatus)
        batteryHealthStatus = view.findViewById(R.id.batteryHealthStatus)
        batteryBatteryTechnology = view.findViewById(R.id.batteryBatteryTechnology)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      val batteryReceiver =  object: BatteryReceiver() {
           override fun onReceive(context: Context?, intent: Intent) {
               val voltage: Int = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0)
               //電壓
               //電壓
               batteryVoltage.setText("" + voltage / 1000 + "." + voltage % 1000 + "V")

               val temperature: Int = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)
               //溫度感測器
               //溫度感測器
               batteryTemperature.setText("" + temperature / 10 + "." + temperature % 10 + "℉")

               val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
               val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0)
               val levelPercent = (level.toFloat() / scale * 100).toInt()
               //目前容量
               //目前容量
               batteryCurrentCapacity.setText((BatteryInfo.getBatteryCapacityVaule(context) / 100 * levelPercent).toString() + " mAh" + "/" + levelPercent + "%")
               //總容量
               //總容量
               batteryTotalCapacity.setText("" + BatteryInfo.getBatteryCapacity(context))
               val status: Int =
                   intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN)
               var strStatus = "Unknown"

               when (status) {
                   BatteryManager.BATTERY_STATUS_CHARGING -> {
                       strStatus = "Charging"
                       //電池狀態
                       batteryBatteryStatus.setText("USB Charging")
                   }
                   BatteryManager.BATTERY_STATUS_DISCHARGING -> {
                       strStatus = "Discharging"
                       //電池狀態
                       batteryBatteryStatus.setText("Uncharged")
                   }
                   BatteryManager.BATTERY_STATUS_NOT_CHARGING -> {
                       strStatus = "Uncharged"
                       //電池狀態
                       batteryBatteryStatus.setText("Uncharged")
                   }
                   BatteryManager.BATTERY_STATUS_FULL -> {
                       strStatus = "Charging completion"
                       //電池狀態
                       batteryBatteryStatus.setText("USB Charging")
                   }
               }
               //充電狀態
               //充電狀態
               batteryChargingStatus.setText("" + strStatus)

               val health: Int =
                   intent.getIntExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN)
               var strHealth = "未知"

               when (health) {
                   BatteryManager.BATTERY_HEALTH_GOOD -> strHealth = "良好"
                   BatteryManager.BATTERY_HEALTH_OVERHEAT -> strHealth = "過熱"
                   BatteryManager.BATTERY_HEALTH_DEAD -> strHealth = "損壞"
                   BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> strHealth = "電壓過高"
                   BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> strHealth = "未知"
                   BatteryManager.BATTERY_HEALTH_COLD -> strHealth = "過冷"
               }
               //健康狀況
               //健康狀況
               batteryHealthStatus.setText("" + strHealth)

               val technology: String? = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)
               //電池技術
               //電池技術
               batteryBatteryTechnology.setText("" + technology)
           }
        }

        //要特別的注意這一句程式碼。註冊一個電池資訊服務
        //要特別的注意這一句程式碼。註冊一個電池資訊服務
        val batteryIntent: Intent =
            context?.applicationContext
                ?.registerReceiver(batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))!!

        /*也可以在這裡獲取，通過batteryIntent .***
           *例如：”batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);//當前電量
           */
    }

    override fun onResume() {
        super.onResume()

    }


}