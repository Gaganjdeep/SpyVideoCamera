package ggn.ameba.spycam.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ggn.ameba.spycam.R;

public class RecordBackGroundActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_back_ground);
    }


    boolean play = true;


    public void playPause(View view)
    {
        view.setBackgroundResource(play ? R.drawable.ic_stop : R.drawable.ic_play);

        play = !play;
    }
}
