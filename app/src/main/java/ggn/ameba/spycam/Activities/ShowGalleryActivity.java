package ggn.ameba.spycam.Activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import ggn.ameba.spycam.CustomGallery.GalleryActivites.GalleryFragment;
import ggn.ameba.spycam.CustomGallery.GalleryActivites.VideoFragment;
import ggn.ameba.spycam.R;
import ggn.ameba.spycam.service_background.BackgroundVideoRecorder;
import ggn.ameba.spycam.service_background.CamerService;
import ggn.ameba.spycam.utills.Gallery;

public class ShowGalleryActivity extends BaseActivityG
{


    private String title;
    private int    theme;
    private Fragment fragment = null;


    private Class serviceClass;


    boolean isCamera = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Gallery gallery = Gallery.valueOf(getIntent().getIntExtra("gallery", 11));


        switch (gallery)
        {
            case IMAGE_CLICK:

                fragment = new GalleryFragment();
                title = "Images";

                theme = R.style.AppThemeCamera;

                serviceClass = CamerService.class;
                isCamera = true;

                break;

            case VIDEO_CLICK:

                fragment = new VideoFragment();
                title = "Videos";


                theme = R.style.AppThemeCamera;

                serviceClass = CamerService.class;
                isCamera = true;

                break;

            case IMAGE_VIDEO:

                fragment = new GalleryFragment();
                title = "Images";

                theme = R.style.AppThemeVideo;

                serviceClass = BackgroundVideoRecorder.class;
                break;


            case VIDEO_VIDEO:

                fragment = new VideoFragment();
                title = "Videos";

                theme = R.style.AppThemeVideo;
                serviceClass = BackgroundVideoRecorder.class;
                break;

        }

        setTheme(theme);


        setContentView(R.layout.activity_show_gallery);


        imageCount = getLocaldata().getNumberOfImages();

        getLocaldata().setCurrentTheme(theme);


        settingActionBar(title);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

        doBindService();
    }


    private ServiceConnection mConnection = new ServiceConnection()
    {
        public void onServiceConnected(ComponentName className, IBinder service)
        {
            // This is called when the connection with the service has been
            // established, giving us the service object we can use to
            // interact with the service.  Because we have bound to a explicit
            // service that we know is running in our own process, we can
            // cast its IBinder to a concrete class and directly access it.
//            camerService = ((CamerService) service).getService();

        }

        public void onServiceDisconnected(ComponentName className)
        {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            // Because it is running in our same process, we should never
            // see this happen.
//            mBoundService = null;

            if (isCamera && getLocaldata().getNumberOfImages() > imageCount)
            {
                doBindService();

                imageCount++;
            }


        }
    };


    int imageCount = 1;


    Intent serviceIntent;

    void doBindService()
    {
        serviceIntent = getBackgroundIntent(serviceClass);

        startService(serviceIntent);
    }


    void doUnbindService()
    {
        if (serviceIntent != null)
        {
            stopService(serviceIntent);
        }
    }


    @Override
    protected void onDestroy()
    {
        doUnbindService();
        super.onDestroy();
    }

    private void settingActionBar(String title)
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back_img);


        ((TextView) toolbar.findViewById(R.id.tvTitle)).setText(title);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {


        finish();


        return super.onOptionsItemSelected(item);
    }


}
