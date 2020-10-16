package lyon.com.fetdoctor.API

import android.content.Context
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import lyon.com.fetdoctor.R
import lyon.com.fetdoctor.Base.AppController
import lyon.com.fetdoctor.Tool.LogL

val MY_SOCKET_TIMEOUT_MS = 30*1000


class VolletTool {
    val TAG = this::class.java.simpleName
    internal var context: Context
    lateinit  var onCallPathBack: OnCallPathBack
    lateinit var onError: OnError
    lateinit var queue :RequestQueue
    constructor(context: Context){
        this.context = context
        if(!::queue.isInitialized) {
            queue = Volley.newRequestQueue(this.context)
        }
    }

    fun CallPath(httpUrl:String)
    {
        if(!::queue.isInitialized){
            queue = Volley.newRequestQueue(context)
        }


        LogL.d(TAG, "url:"+httpUrl)
        val stringRequest = StringRequest(
            Request.Method.GET, httpUrl,
            Response.Listener<String> { response ->
                LogL.d(TAG,"response:"+response)
                if(::onCallPathBack.isInitialized){
                    onCallPathBack.onResponse(response)
                }
            },
            Response.ErrorListener {
                checkError(context,it)
                LogL.d(TAG,"error:"+it)
                if(::onError.isInitialized){
                    onError.Error(it)
                }
            }
        )

        stringRequest.setRetryPolicy(
            DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
        )
        // Add the request to the RequestQueue.
        if(!::queue.isInitialized)
            queue = Volley.newRequestQueue(context)
        queue.add(stringRequest)

    }


    interface OnCallPathBack {
        fun onResponse(response:String)
    }

    interface OnError {
        fun Error(error: VolleyError)
    }

    fun setOnCallPathBackListener(onCallPathBack: OnCallPathBack) {
        this.onCallPathBack = onCallPathBack
    }
    fun setOnErrorListener(onError: OnError) {
        this.onError = onError
    }


    fun checkError(context: Context,error: VolleyError){
        if(error!=null){
            if(error is TimeoutError){
                val errorString = context.getString(R.string.net_overtime)
                if(AppController().getLDeBug())
                Toast.makeText(context,errorString,Toast.LENGTH_SHORT).show();
                LogL.e(TAG,errorString)

                return;
            }
            if(error is ServerError) {
                val errorString = context.getString(R.string.server_error)
                if(AppController().getLDeBug())
                    Toast.makeText(context,errorString,Toast.LENGTH_SHORT).show();
                LogL.e(TAG,errorString)

                return;
            }
            if(error is NetworkError) {
                val errorString = context.getString(R.string.check_network)
                if(AppController().getLDeBug())
                    Toast.makeText(context,errorString,Toast.LENGTH_SHORT).show();
                LogL.e(TAG,errorString)

                return;
            }
            if(error is ParseError) {
                val errorString = context.getString(R.string.data_fail)
                if(AppController().getLDeBug())
                    Toast.makeText(context,errorString,Toast.LENGTH_SHORT).show();
                LogL.e(TAG,errorString)

                return;
            }
            if (error is RedirectError) {
                val errorString = context.getString(R.string.RedirectError)
                if(AppController().getLDeBug())
                    Toast.makeText(context,errorString,Toast.LENGTH_SHORT).show();
                LogL.e(TAG,errorString)

                return ;
            }
            if (error is AuthFailureError) {
                val errorString = context.getString(R.string.AuthFailureError)
                if(AppController().getLDeBug())
                    Toast.makeText(context,errorString,Toast.LENGTH_SHORT).show();
                LogL.e(TAG,errorString)

                return ;
            }
            if(AppController().getLDeBug())
                Toast.makeText(context,error.toString(),Toast.LENGTH_SHORT).show();
            LogL.e(TAG,"ErrorListener:"+error.toString())
        }

    }
}