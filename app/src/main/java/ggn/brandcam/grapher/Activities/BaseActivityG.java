package ggn.brandcam.grapher.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ggn.brandcam.grapher.utills.SharedPrefHelper;
import ggn.brandcam.grapher.utills.UtillsG;

/**
 * Created by gagandeep on 24 May 2016.
 */
public class BaseActivityG extends AppCompatActivity
{

    SharedPrefHelper localdata;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        localdata = new SharedPrefHelper(getApplicationContext());

        UtillsG.changeLanguage(this, getLocaldata().getlanguage());
        super.onCreate(savedInstanceState);
    }


    public SharedPrefHelper getLocaldata()
    {
        return localdata;
    }


    public Intent getBackgroundIntent(Class serviceClass)
    {
        boolean frontCam;
        boolean flash;

        if (serviceClass.getName().contains("CamerService"))
        {
            frontCam = getLocaldata().isFrontCamCamera();
            flash = getLocaldata().isFlashOnCamera();
        }
        else
        {
            frontCam = getLocaldata().isFrontCamVideo();
            flash = getLocaldata().isFlashOnVideo();
        }


        Intent serviceIntent = new Intent(this, serviceClass);
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


        return serviceIntent;
    }










/*    Dialog dialog;

    public void showProgress() {
        dialog = new Dialog(this, R.style.Theme_Dialog);
        dialog.setContentView(R.layout.progress_dialog);
        dialog.setCancelable(false);
        dialog.show();

    }

    public void cancelProgress() {
        if (dialog != null) {
            dialog.cancel();
        }
    }*/
}
