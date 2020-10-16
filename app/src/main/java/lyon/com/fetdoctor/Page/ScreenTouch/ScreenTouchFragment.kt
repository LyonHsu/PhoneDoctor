package lyon.com.fetdoctor.Page.ScreenTouch


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.screen_touch_fragment.*
import lyon.com.fetdoctor.R


/**
 * 廣播監聽獲取到電池的資訊
 */

class ScreenTouchFragment : Fragment() {
    val TAG = this::class.java.simpleName
    lateinit var btn:Button
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.screen_touch_fragment, container, false)
        btn = view.findViewById(R.id.btn)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn.setOnClickListener {
            var intent = Intent(activity,ScreenTouchActivity::class.java)
            activity?.startActivity(intent)
        }
    }


    override fun onResume() {
        super.onResume()

    }


}