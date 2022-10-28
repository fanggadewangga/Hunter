package com.raion.hunter.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.raion.hunter.MainActivity
import com.raion.hunter.R

fun createChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = NotificationChannel(
            CHANNEL_ID,
            context.getString(R.string.channel_name),

            NotificationManager.IMPORTANCE_HIGH
        )
            .apply {
                setShowBadge(false)
            }

        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.description = context.getString(R.string.notification_channel_description)

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(notificationChannel)
    }
}

fun NotificationManager.sendGeofenceEnteredNotification(context: Context, fenceId: String) {
    val contentIntent = Intent(context, MainActivity::class.java)
    contentIntent.putExtra(GeofencingConstants.EXTRA_GEOFENCE, fenceId)
    val contentPendingIntent = PendingIntent.getActivity(
        context,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )
    val mapImage = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.map_small
    )
    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(mapImage)
        .bigLargeIcon(null)

    // We use the name resource ID from the LANDMARK_DATA along with content_text to create
    // a custom message when a Geofence triggers.
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle(context.getString(R.string.app_name))
        .setContentText(context.getString(R.string.content_text))
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(contentPendingIntent)
        .setSmallIcon(R.drawable.map_small)
        .setStyle(bigPicStyle)
        .setLargeIcon(mapImage)

    notify(NOTIFICATION_ID, builder.build())
}

private const val NOTIFICATION_ID = 33
private const val CHANNEL_ID = "GeofenceChannel"