package lyon.com.fetdoctor.Tool

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Toast
import java.util.*

object Speech  {
    open fun startListeningDialog(context: Activity) {
        // Intent to listen to user vocal input and return the result to the same activity.
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        // Use a language model based on free-form speech recognition.
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5)
        intent.putExtra(
            RecognizerIntent.EXTRA_CALLING_PACKAGE,
            context.getPackageName()
        )

        try {
            context.startActivityForResult(intent, 200)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(context, "Intent problem", Toast.LENGTH_SHORT).show()
        }
    }
}