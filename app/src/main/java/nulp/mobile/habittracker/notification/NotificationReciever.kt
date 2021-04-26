package nulp.mobile.habittracker.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationReciever : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val service = Intent(context, NotificationService::class.java)
        service.putExtra("reason", intent.getStringExtra("reason"))
        service.putExtra("timestamp", intent.getLongExtra("timestamp", 0))
        service.putExtra("title", intent.getStringExtra("title"))
        service.putExtra("message", intent.getStringExtra("message"))

        context.startService(service)
    }

}