package ggn.ameba.spycam.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ggn.ameba.spycam.R;
import ggn.ameba.spycam.utills.Gallery;

public class RecordAudioActivity extends BaseActivityG
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_audio);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void recordBackground(View view)
    {
        startActivity(new Intent(RecordAudioActivity.this, RecordBackGroundActivity.class));
    }

    public void bySendingSMS(View view)
    {
    }
}
