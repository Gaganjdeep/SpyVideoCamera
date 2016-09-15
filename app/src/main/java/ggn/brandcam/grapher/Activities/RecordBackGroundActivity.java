package ggn.brandcam.grapher.Activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ggn.brandcam.grapher.R;
import ggn.brandcam.grapher.service_background.AudioRecorderService;
import ggn.brandcam.grapher.service_background.BackgroundVideoRecorder;
import ggn.brandcam.grapher.service_background.CamerService;
import ggn.brandcam.grapher.utills.Background;
import ggn.brandcam.grapher.utills.ReceiverCamera;

public class RecordBackGroundActivity extends BaseActivityG
{

    Background background;

    ReceiverCamera receiverCamera;
    Intent         serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        background = Background.valueOf(getIntent().getIntExtra("background", 3));

        switch (background)
        {
            case IMAGE:
                setTheme(R.style.AppThemeCamera);

                serviceIntent = getBackgroundIntent(CamerService.class);

                receiverCamera = new ReceiverCamera();


                break;
            case VIDEO:
                setTheme(R.style.AppThemeVideo);


                serviceIntent = getBackgroundIntent(BackgroundVideoRecorder.class);


                break;
            case AUDIO:
                setTheme(R.style.AppThemeAudio);
                serviceIntent = new Intent(RecordBackGroundActivity.this, AudioRecorderService.class);
                break;
        }

        setContentView(R.layout.activity_record_back_ground);


        if (receiverCamera != null)
        {
            registerReceiver(receiverCamera, new IntentFilter(ReceiverCamera.CLICK_IMAGE));
        }


        CamerService.count = 0;


    }


    boolean play = true;


    public void playPause(View view)
    {
        ((ImageView) view).setImageResource(play ? R.drawable.ic_stop : R.drawable.ic_play);

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

        if (receiverCamera != null)
        {
            unregisterReceiver(receiverCamera);
        }

        super.onDestroy();
    }
}
