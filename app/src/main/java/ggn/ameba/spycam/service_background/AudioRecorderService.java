package ggn.ameba.spycam.service_background;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;

import java.io.File;

import ggn.ameba.spycam.R;
import ggn.ameba.spycam.utills.SharedPrefHelper;

/**
 * Created by gagandeep on 31 May 2016.
 */
public class AudioRecorderService extends Service
{
    private MediaRecorder myRecorder;
//    private String outputFile = null;

    public IBinder onBind(Intent arg0)
    {
        return null;
    }


    @Override
    public void onCreate()
    {

        // Start foreground service to avoid unexpected kill

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
        {
            Notification notification = null;
            notification = new Notification.Builder(this)
                    .setContentTitle("Recording Audio")
                    .setContentText("")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .build();
            startForeground(22, notification);
        }


        File imagesFolder = new File(
                Environment.getExternalStorageDirectory(), new SharedPrefHelper(this).getFolderName());
        if (!imagesFolder.exists())
            imagesFolder.mkdirs(); // <----


        File audio = new File(imagesFolder, System.currentTimeMillis()
                + ".3gpp");


        myRecorder = new MediaRecorder();
        myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myRecorder.setOutputFile(audio.getPath());

        try
        {
            myRecorder.prepare();
            myRecorder.start();
        }
        catch (Exception | Error e)

        {
            // start:it is called before prepare()
            // prepare: it is called after start() or before setOutputFormat()
            e.printStackTrace();
        }


    }

    public int onStartCommand(Intent intent, int flags, int startId)
    {
        //onCreate();
//        // return 1;
//        return START_STICKY; //not sure about this one
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy()
    {
        try
        {
            myRecorder.stop();
            myRecorder.reset();
            myRecorder.release();
            myRecorder = null;
        }
        catch (Exception | Error e)
        {
            e.printStackTrace();
        }

    }


}