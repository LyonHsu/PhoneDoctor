package lyon.com.fetdoctor.Page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import lyon.com.fetdoctor.R

const val ARG_OBJECT = "object"
class DemoObjectFragment : Fragment() {
    val TAG = this::class.java.simpleName
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_object, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val textView: TextView = view.findViewById(R.id.text1)
            textView.text = getInt(ARG_OBJECT).toString()
        }
    }
}