/*
 * Receive and transmit an SMS. All the work is done in the Broadcast Receiver.
 * Be sure to notice the intent filter and permissions in the Manifest.
 * These are both considered dangerous permissions so go into Settings on the
 * emulator for this application to allow SMS at runtime.
 * To send an SMS use the emulator extended control panel.
 *
 */

//

package com.course.example.smsreceiver;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SmsReceiver extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i("SMS", "App Started"); 
    }
}