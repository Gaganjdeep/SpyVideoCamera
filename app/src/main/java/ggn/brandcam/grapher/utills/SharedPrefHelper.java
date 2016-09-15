package ggn.brandcam.grapher.utills;

import android.content.Context;
import android.content.SharedPreferences;

import ggn.brandcam.grapher.R;

/**
 * Created by gagandeep on 28 Mar 2016.
 */
public class SharedPrefHelper
{


    SharedPreferences shared;

    public SharedPrefHelper(Context context)
    {
        shared = context.getSharedPreferences("SpyCamGj", Context.MODE_PRIVATE);

    }


    //    NUMBER OF IMAGES TO CLICK START

    public void setCurrentTheme(int count)
    {
        SharedPreferences.Editor ed = shared.edit();
        ed.putInt("CurrentTheme", count);
        ed.apply();
    }


    public int getCurrentTheme()
    {
        return shared.getInt("CurrentTheme", R.style.AppThemeCamera);
    }
//    NUMBER OF IMAGES TO CLICK END


    //    NUMBER OF IMAGES TO CLICK START

    public void setNumberofImages(int count)
    {
        SharedPreferences.Editor ed = shared.edit();
        ed.putInt("numberofImages", count);
        ed.apply();
    }


    public int getDelayInImages()
    {
        return shared.getInt("DelayInImages", 1);
    }
//    NUMBER OF IMAGES TO CLICK END


    //    delay OF IMAGES TO CLICK START

    public void setDelayInImages(int count)
    {
        SharedPreferences.Editor ed = shared.edit();
        ed.putInt("DelayInImages", count);
        ed.apply();
    }


    public int getNumberOfImages()
    {
        return shared.getInt("numberofImages", 1);
    }
//    delay OF IMAGES TO CLICK END


    //    NAME OF FOLDER TO SAVE IMAGES START

    public void setFolderName(String folderName)
    {
        SharedPreferences.Editor ed = shared.edit();
        ed.putString("folderName", folderName);
        ed.apply();
    }


    public String getFolderName()
    {
        return shared.getString("folderName", "SpyCamG");
    }
    //    NAME OF FOLDER TO SAVE IMAGES END


//    FLASH STUFF START

    public void setFlashOnCamera(boolean flash)
    {
        SharedPreferences.Editor ed = shared.edit();
        ed.putBoolean("flashCamera", flash);
        ed.apply();
    }


    public boolean isFlashOnCamera()
    {
        return shared.getBoolean("flashCamera", false);
    }

    public void setFlashOnVideo(boolean flash)
    {
        SharedPreferences.Editor ed = shared.edit();
        ed.putBoolean("flashVideo", flash);
        ed.apply();
    }


    public boolean isFlashOnVideo()
    {
        return shared.getBoolean("flashVideo", false);
    }


//    FLASH STUFF END


    //    CAM SELECTION STUFF START
    public void setFrontCamVideo(boolean frontCam)
    {
        SharedPreferences.Editor ed = shared.edit();
        ed.putBoolean("frontCamVideo", frontCam);
        ed.apply();
    }


    public boolean isFrontCamVideo()
    {
        return shared.getBoolean("frontCamVideo", true);
    }

    public void setFrontCamCamera(boolean frontCam)
    {
        SharedPreferences.Editor ed = shared.edit();
        ed.putBoolean("frontCamCamera", frontCam);
        ed.apply();
    }


    public boolean isFrontCamCamera()
    {
        return shared.getBoolean("frontCamCamera", true);
    }


    public void setMsgAudioRecording(boolean yesNO)
    {
        SharedPreferences.Editor ed = shared.edit();
        ed.putBoolean("MsgAudioRecording", yesNO);
        ed.apply();
    }


    public boolean isMsgAudioRecording()
    {
        return shared.getBoolean("MsgAudioRecording", true);
    }


    //    set language

    public void setLanguage(String language)
    {
        SharedPreferences.Editor ed = shared.edit();
        ed.putString("language", language);
        ed.apply();
    }


    public String getlanguage()
    {
        return shared.getString("language", "en");
    }
    //    set language END


}
