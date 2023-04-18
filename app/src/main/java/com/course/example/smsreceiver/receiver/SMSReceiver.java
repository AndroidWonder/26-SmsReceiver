/* This example reads a message and responds to sender.

Remember to set permission in Settings to receive an SMS.
 */

package com.course.example.smsreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SMSBroadcastReceiver";
    private SmsMessage[] smsMessage;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Intent recieved: " + intent.getAction());

        //read messages from hardware into an array

            Bundle bundle = intent.getExtras();
            if(bundle == null) Log.e(TAG, "null bundle");

            //get messages
            SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

            //create SmsManager
            SmsManager sms = SmsManager.getDefault();

            //read each message and send acknowledgement to sender SMS
            for (SmsMessage message : smsMessages) {
                //log message body
                Log.i(TAG, "Message recieved: " + message.getDisplayMessageBody());

                //send acknowledgement to receiver
                String to = message.getOriginatingAddress();
                Toast.makeText(context, "SMS Originator: " + to, Toast.LENGTH_LONG).show();
                Log.i(TAG, to);
                sms.sendTextMessage(to, null, "Message Acknowledged", null, null);
                Toast.makeText(context, "Reply Sent to " + to, Toast.LENGTH_LONG).show();
                Log.i(TAG, "SMS Acknowledge Sent");
            }
            // End of loop

    }
}
