package ggn.scamo.grapher.Activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.view.WindowManager;

import ggn.scamo.grapher.R;

public class SplashActivity extends BaseActivityG
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        startUp();
    }


    private void startUp()
    {
        if (ContextCompat.checkSelfPermission(SplashActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(SplashActivity.this,
                Manifest.permission.SYSTEM_ALERT_WINDOW)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(SplashActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(SplashActivity.this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(SplashActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(SplashActivity.this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {


// Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,
                    Manifest.permission.CAMERA)
                    ||
                    ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,
                            Manifest.permission.SYSTEM_ALERT_WINDOW)
                    ||
                    ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    ||
                    ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,
                            Manifest.permission.RECORD_AUDIO)
                    ||
                    ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                    ||
                    ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,
                            Manifest.permission.RECEIVE_SMS)
                    )

            {

// Show an expanation to the user *asynchronously* -- don't block
// this thread waiting for the user's response! After the user
// sees the explanation, try again to request the permission.
                final AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                builder.setMessage("WhereZat allows to access your contact and location information.")
                        .setCancelable(false)
                        .setPositiveButton("Allow", new DialogInterface.OnClickListener()
                        {
                            public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id)
                            {
                                ActivityCompat.requestPermissions(SplashActivity.this,
                                        new String[]{Manifest.permission.CAMERA,
                                                Manifest.permission.SYSTEM_ALERT_WINDOW,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                Manifest.permission.RECORD_AUDIO,
                                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                                Manifest.permission.RECEIVE_SMS},
                                        11);


                            }
                        })
                        .setNegativeButton("Exit", new DialogInterface.OnClickListener()
                        {
                            public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id)
                            {
                                dialog.cancel();
                                finish();
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();

            }
            else
            {
// No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(SplashActivity.this,
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.SYSTEM_ALERT_WINDOW,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.RECORD_AUDIO,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.RECEIVE_SMS},
                        11);


            }

        }
        else
        {
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    startActivity(new Intent(SplashActivity.this, MenuActivity.class));
                }
            }, 1000);

        }
    }


}
