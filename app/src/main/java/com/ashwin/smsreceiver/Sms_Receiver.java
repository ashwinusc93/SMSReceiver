package com.ashwin.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;

/**
 * Created by Dell on 10-04-2015.
 */
public class Sms_Receiver extends BroadcastReceiver {
    public static String event;
    public Sms_Receiver()
    {
    }

    public void onReceive(Context context, Intent intent) {

        Bundle bundle;
        String s;
        bundle = intent.getExtras();
        s = "";

        Object aobj[];
        int i;
        aobj = (Object[])bundle.get("pdus");
        i = 0;


        SmsMessage smsmessage = SmsMessage.createFromPdu((byte[])aobj[i]);
        String s1 = smsmessage.getMessageBody();
        String s2 = smsmessage.getOriginatingAddress();
        s = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s))).append("SMS from ").append(s2).append(" :\n").toString()))).append(s1).append("\n").toString();
        String s3 = s2.replace("+91", "");
        String s4 = s1.trim();
        System.out.println((new StringBuilder("sender =\")).append(s3).toString());\n" +
                "        System.out.println((new StringBuilder(\"   message =")).append(s4).toString());
        try
        {
            if ( s3.equals("DM-SitSMS") && s4.contains("Fire"))
            {
                event="fire";
               // Toast.makeText(context, (new StringBuilder("sender =")).append(s3).append("   message =").append(s4).toString(), Toast.LENGTH_SHORT).show();
                Simple_Notification.send_Notification(context);
            }else
                if(s3.equals("DM-SitSMS") && s4.contains("door")){
                event ="door";
                    Simple_Notification.send_Notification(context);
                }
        }
        catch (Exception exception)
        {
            System.out.println((new StringBuilder("EeEEEE")).append(exception.getMessage()).toString());
        }
        i++;

    }
}
