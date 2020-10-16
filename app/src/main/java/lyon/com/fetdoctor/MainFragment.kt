package lyon.com.fetdoctor


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import lyon.com.fetdoctor.Page.MainPagerAdapter


/**
 *
 */
class MainFragment : Fragment() {
    val TAG = this::class.java.simpleName
    private lateinit var pagerAdapter: MainPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.main_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pagerAdapter = MainPagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = pagerAdapter

        tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        for(i in 0.. pagerAdapter.count-1)
            tabLayout.getTabAt(i)!!.setCustomView(tab_icon(pagerAdapter.getPageTitle(i).toString(),R.drawable.ic_menu_share))
    }

    override fun onResume() {
        super.onResume()

    }

    fun tab_icon( name:String,  iconID:Int):View{
        val newtab: View = LayoutInflater.from(context).inflate(R.layout.icon_view, null)
        val tv = newtab.findViewById<View>(R.id.tabtext) as TextView
        tv.text = name
        val im: ImageView = newtab.findViewById<View>(R.id.tabicon) as ImageView
        im.setImageResource(iconID)
        return newtab
    }
}