package lyon.com.fetdoctor.Tool

import android.content.Context

object BatteryInfo {
    /**
     * 獲取電池容量 mAh
     *
     * 源標頭檔案:frameworks/base/core/res\res/xml/power_profile.xml
     *
     * Java 反射檔案：frameworks\base\core\java\com\android\internal\os\PowerProfile.java
     */
    fun getBatteryCapacity(context: Context?): String {
        var batteryCapacity = getBatteryCapacityVaule(context)
        return "$batteryCapacity mAh"
    }

    fun getBatteryCapacityVaule(context: Context?): Double {
        val mPowerProfile: Any
        var batteryCapacity = 0.0
        val POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile"
        try {
            mPowerProfile = Class.forName(POWER_PROFILE_CLASS)
                .getConstructor(Context::class.java)
                .newInstance(context)
            batteryCapacity = Class
                .forName(POWER_PROFILE_CLASS)
                .getMethod("getBatteryCapacity")
                .invoke(mPowerProfile) as Double
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return batteryCapacity
    }
}