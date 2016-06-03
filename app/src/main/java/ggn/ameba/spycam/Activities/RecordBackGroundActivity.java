package ggn.ameba.spycam.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ggn.ameba.spycam.R;
import ggn.ameba.spycam.service_background.AudioRecorderService;
import ggn.ameba.spycam.service_background.BackgroundVideoRecorder;
import ggn.ameba.spycam.service_background.CamerService;
import ggn.ameba.spycam.utills.Background;
import ggn.ameba.spycam.utills.Gallery;

public class RecordBackGroundActivity extends BaseActivityG
{

    Background background;


    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        background = Background.valueOf(getIntent().getIntExtra("background", 3));

        switch (background)
        {
            case IMAGE:
                setTheme(R.style.AppThemeCamera);

                serviceIntent = new Intent(RecordBackGroundActivity.this, CamerService.class);
                serviceIntent.putExtra("Quality_Mode", 0);
                if (!getLocaldata().isFrontCamCamera())
                {
                    if (getLocaldata().isFlashOnCamera())
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


                break;
            case VIDEO:
                setTheme(R.style.AppThemeVideo);


                serviceIntent = new Intent(RecordBackGroundActivity.this, BackgroundVideoRecorder.class);
                serviceIntent.putExtra("Quality_Mode", 0);
                if (!getLocaldata().isFrontCamCamera())
                {
                    if (getLocaldata().isFlashOnCamera())
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


                break;
            case AUDIO:
                setTheme(R.style.AppThemeAudio);
                serviceIntent = new Intent(RecordBackGroundActivity.this, AudioRecorderService.class);
                break;
        }

        setContentView(R.layout.activity_record_back_ground);


    }


    boolean play = true;


    public void playPause(View view)
    {
        view.setBackgroundResource(play ? R.drawable.ic_stop : R.drawable.ic_play);

        if (play)
        {
            startService(serviceIntent);
        }
        else
        {
            finish();
        }


        play = !play;
    }


    @Override
    protected void onDestroy()
    {
        if (serviceIntent != null)
        {
            stopService(serviceIntent);
        }
        super.onDestroy();
    }
}
