package ggn.brandcam.grapher.utills;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;

import ggn.brandcam.grapher.service_background.CamerService;

/**
 * Created by gagandeep on 03 Jun 2016.
 */
public class ReceiverCamera extends BroadcastReceiver
{

    public static String CLICK_IMAGE = "ggn.click.image.j";

    public static void getBackgroundIntent(Context context, Class serviceClass)
    {

        SharedPrefHelper localData = new SharedPrefHelper(context);

        boolean frontCam;
        boolean flash;

//        if (serviceClass.getName().contains("CamerService"))
//        {
        frontCam = localData.isFrontCamCamera();
        flash = localData.isFlashOnCamera();
//        }
//        else
//        {
//            frontCam = localData.isFrontCamVideo();
//            flash = localData.isFlashOnVideo();
//        }


        Intent serviceIntent = new Intent(context, serviceClass);
        serviceIntent.putExtra("Quality_Mode", 0);


        if (!frontCam)
        {
            if (flash)
            {
                serviceIntent.putExtra("FLASH", "auto");
            }
            else
            {
                serviceIntent.putExtra("FLASH", "off");
            }
            serviceIntent.putExtra("Front_Request", false);
        }
        else
        {
            serviceIntent.putExtra("Front_Request", true);
        }


        context.startService(serviceIntent);
    }


    @Override
    public void onReceive(final Context context, Intent intent)
    {

        try
        {
            SharedPrefHelper sharedPrefHelper = new SharedPrefHelper(context);

            new CountDownTimer(sharedPrefHelper.getDelayInImages(), sharedPrefHelper.getDelayInImages())
            {

                @Override
                public void onTick(long l)
                {

                }

                @Override
                public void onFinish()
                {
                    getBackgroundIntent(context, CamerService.class);
                }
            }.start();
        }
        catch (Exception e)
        {
            getBackgroundIntent(context, CamerService.class);

            e.printStackTrace();
        }

    }
}
