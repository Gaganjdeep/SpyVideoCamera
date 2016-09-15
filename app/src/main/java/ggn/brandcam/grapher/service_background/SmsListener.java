package ggn.brandcam.grapher.service_background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import ggn.brandcam.grapher.utills.SharedPrefHelper;
import ggn.brandcam.grapher.utills.UtillsG;

/**
 * Created by gagandeep on 06 Jun 2016.
 */
public class SmsListener extends BroadcastReceiver
{
    String msgBody;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
        {
            Bundle       bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs   = null;
            String       msg_from;
            if (bundle != null)
            {
                //---retrieve the SMS message received---
                try
                {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++)
                    {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        msgBody = msgs[i].getMessageBody();
                    }

                    if (msgBody.equalsIgnoreCase("start recording"))
                    {
                        if (new SharedPrefHelper(context).isMsgAudioRecording())
                        {
                            context.startService(new Intent(context, AudioRecorderService.class));
                        }
                    }
                    else if (msgBody.equalsIgnoreCase("stop recording"))
                    {
                        if (UtillsG.isMyServiceRunning(AudioRecorderService.class, context))
                        {
                            context.stopService(new Intent(context, AudioRecorderService.class));
                        }
                    }


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}