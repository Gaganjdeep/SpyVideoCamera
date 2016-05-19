package ggn.ameba.spycam.service_background;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by gagandeep on 28 Mar 2016.
 */
public class SharedPrefHelper
{


    SharedPreferences shared;

    public SharedPrefHelper(Context context)
    {
        shared = context.getSharedPreferences("SpyCamG", Context.MODE_PRIVATE);

    }


    public void setCaptureOnUnlock(boolean capture)
    {
        SharedPreferences.Editor ed = shared.edit();
        ed.putBoolean("captureOnUnlock", capture);
        ed.apply();
    }


    public boolean isCaptureOnUnlock()
    {
        return shared.getBoolean("captureOnUnlock", true);
    }


    public void setFlashOn(boolean flash)
    {
        SharedPreferences.Editor ed = shared.edit();
        ed.putBoolean("flash", flash);
        ed.apply();
    }


    public boolean isFlashOn()
    {
        return shared.getBoolean("flash", false);
    }


    public void setFrontCam(boolean frontCam)
    {
        SharedPreferences.Editor ed = shared.edit();
        ed.putBoolean("frontCam", frontCam);
        ed.apply();
    }


    public boolean isFrontCam()
    {
        return shared.getBoolean("frontCam", true);
    }


}
