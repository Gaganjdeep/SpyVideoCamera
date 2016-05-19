package ggn.ameba.spycam;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import ggn.ameba.spycam.service_background.BackgroundVideoRecorder;
import ggn.ameba.spycam.service_background.ServiceMovableCameraBtn;
import ggn.ameba.spycam.service_background.SharedPrefHelper;

public class MainActivityG extends AppCompatActivity implements View.OnClickListener
{


    SwitchCompat switchbtn;


    SharedPrefHelper sharedPrefHelper;

    FloatingActionButton btnFlash;

    TextView tvFrontCam, tvRearCam;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maing);


        sharedPrefHelper = new SharedPrefHelper(this);


        btnFlash = (FloatingActionButton) findViewById(R.id.btnFlash);


        showFlasICon();


        tvFrontCam = (TextView) findViewById(R.id.tvFrontCam);
        tvRearCam = (TextView) findViewById(R.id.tvRearCam);

        switchbtn = (SwitchCompat) findViewById(R.id.switchbtn);


        ChangeCam(sharedPrefHelper.isFrontCam());


        switchbtn.setChecked(sharedPrefHelper.isCaptureOnUnlock());

        switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                sharedPrefHelper.setCaptureOnUnlock(b);
            }
        });


        tvFrontCam.setOnClickListener(this);
        tvRearCam.setOnClickListener(this);

    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private void ChangeCam(boolean frontFacing)
    {
        if (frontFacing)
        {
            tvFrontCam.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            tvRearCam.setBackgroundColor(getResources().getColor(R.color.white));


            btnFlash.setVisibility(View.GONE);

            sharedPrefHelper.setFrontCam(true);
        }
        else
        {
            tvFrontCam.setBackgroundColor(getResources().getColor(R.color.white));
            tvRearCam.setBackgroundColor(getResources().getColor(R.color.colorAccent));

            sharedPrefHelper.setFrontCam(false);

            btnFlash.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed()
    {

        Intent front_translucent = new Intent(MainActivityG.this, BackgroundVideoRecorder.class);
        stopService(
                front_translucent);


        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent front_translucent = new Intent(MainActivityG.this, BackgroundVideoRecorder.class);
        front_translucent.putExtra("Front_Request", true);
        startService(
                front_translucent);

        return super.onOptionsItemSelected(item);
    }

    public void floatingCamera(View view)
    {

        Intent front_translucent = new Intent(MainActivityG.this, ServiceMovableCameraBtn.class);
        startService(front_translucent);
        finish();
    }

    public void flashOnOff(View view)
    {
        showFlasICon();
    }


    private void showFlasICon()
    {
        if (sharedPrefHelper.isFlashOn())
        {
            sharedPrefHelper.setFlashOn(false);
            btnFlash.setImageResource(R.mipmap.flash_off);
        }
        else
        {
            sharedPrefHelper.setFlashOn(true);
            btnFlash.setImageResource(R.mipmap.flash_auto);
        }

    }


    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.tvFrontCam)
        {
            ChangeCam(true);
        }
        else
        {
            ChangeCam(false);
        }
    }
}
