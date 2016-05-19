package ggn.ameba.spycam;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import ggn.ameba.spycam.service_background.CamerService;
import ggn.ameba.spycam.service_background.SharedPrefHelper;

/**
 * Created by gagandeep on 23 Mar 2016.
 */
public class ReceiverG extends WakefulBroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
//        if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION"))
//        {

        if (new SharedPrefHelper(context).isCaptureOnUnlock())
        {
            Intent front_translucent = new Intent(context, CamerService.class);
            front_translucent.putExtra("Front_Request", true);
            front_translucent.putExtra("Quality_Mode", 0);
            context.startService(
                    front_translucent);
        }


    }
}