package lyon.com.fetdoctor.Page

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import lyon.com.fetdoctor.Page.Battery.BatteryFragment
import lyon.com.fetdoctor.Page.ScreenTouch.ScreenTouchFragment

class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){
    val TAG = this::class.java.simpleName
    override fun getCount(): Int  = 10

    override fun getItem(i: Int): Fragment {
        var fragment = Fragment()
        when(i){
            0->fragment = BatteryFragment()
            1-> fragment =
                ScreenTouchFragment()

            else->{
                 fragment = DemoObjectFragment()
                fragment.arguments = Bundle().apply {
                    // Our object is just an integer :-P
                    putInt(ARG_OBJECT, i + 1)
                }
            }
        }
        return fragment

    }

    override fun getPageTitle(position: Int): CharSequence {
        when(position){
            0-> return "電池"
            1-> return "螢幕"
            else-> return "OBJECT ${(position + 1)}"

        }
        return "OBJECT ${(position + 1)}"
    }
}