package com.ashwin.smsreceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Dell on 10-04-2015.
 */
public class Simple_Notification {
    static int NOTIFY_ME_ID = 1;
    static Context context;
    static NotificationManager mgr;

    public Simple_Notification(Context context1)
    {
        context = context1;
    }

    public static void send_Notification(Context context1)
    {
        mgr = (NotificationManager)context1.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(android.R.drawable.
                stat_notify_more, "Android Example Status message!", System.currentTimeMillis());
        if(Sms_Receiver.event.equals("fire")) {
            notification.setLatestEventInfo(context1, "Fire Notification", "Fire occured.Please shut off the valve", PendingIntent.getActivity(context1, 0, new Intent(context1, AfterNotication.class), 0));
            mgr.notify(NOTIFY_ME_ID, notification);
        }
        else
            if(Sms_Receiver.event.equals("door")){
                notification.setLatestEventInfo(context1, "Door opened", "Please close the door", PendingIntent.getActivity(context1, 0, new Intent(context1, DoorEvent.class), 0));
                mgr.notify(NOTIFY_ME_ID, notification);
            }
    }
}
