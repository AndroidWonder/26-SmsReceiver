/* This example reads an Sms message as a protocol data unit(pdu), which is the
industry standard format for an Sms message. Large messages may be broken into
several so we use an array of Objects.
 */

package com.course.example.smsreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
        if (intent.getAction().equals(SMS_RECEIVED)) {

            Bundle bundle = intent.getExtras();
            if(bundle == null) Log.e(TAG, "null bundle");
            Object[] messages = (Object[]) bundle.get("pdus");
            smsMessage = new SmsMessage[messages.length];
            for (int n = 0; n < messages.length; n++) {
                smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
            }
            if (messages.length > -1) {
                Log.i(TAG, "Message recieved: " + smsMessage[0].getMessageBody());
            }
        }
        //read each message and send acknowledgement to sender SMS
        SmsManager sms = SmsManager.getDefault();

        for( SmsMessage msg:smsMessage){

            //show message body
            Toast.makeText(context, "Received SMS: " + msg.getMessageBody(), Toast.LENGTH_LONG).show();
            Log.i(TAG,"SMS Received");

            String to = msg.getOriginatingAddress();
            Toast.makeText(context, "SMS Originator: " + to, Toast.LENGTH_LONG).show();
            Log.i(TAG, to);
            sms.sendTextMessage(to, null, "Message Acknowledged", null, null);
            Toast.makeText(context, "Reply Sent to " + to, Toast.LENGTH_LONG).show();
            Log.i(TAG, "SMS Acknowledge Sent");
        }
    }
}
