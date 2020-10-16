package lyon.com.fetdoctor.Page.ScreenTouch

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import lyon.com.fetdoctor.Object.TouchButton
import lyon.com.fetdoctor.Tool.LogL


open class ScreenTouchAdapter (val context: Context): RecyclerView.Adapter<ScreenTouchAdapter.MyViewHolder>() {
    val TAG = this::class.java.simpleName
    var size = getCount()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScreenTouchAdapter.MyViewHolder {
        val view = object:TouchButton(context){
            override fun viewHide(){
                size--
                LogL.d(TAG,"size:"+size)
                if(size<=0){
                    buttonClear()
                }

            }
        }
        val holder = MyViewHolder(view)
       return holder
    }

    override fun getItemCount(): Int {
       return size
    }




    override fun onBindViewHolder(holder: ScreenTouchAdapter.MyViewHolder, position: Int) {

    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    open fun buttonClear(){

    }

    fun getCount():Int{
        val diaplay = context.resources.displayMetrics
        val w = diaplay.widthPixels
        val h = diaplay.heightPixels
        var size = 6*10
        LogL.d(TAG,"size:"+size)
        return 60
    }

    public fun getTouchSize():Int{
        return size
    }

}