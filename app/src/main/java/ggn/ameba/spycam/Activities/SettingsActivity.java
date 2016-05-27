package ggn.ameba.spycam.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ggn.ameba.spycam.R;

public class SettingsActivity extends BaseActivityG
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void chooseLanguage(View view)
    {
    }

    public void comMent(View view)
    {
    }

    public void inFo(View view)
    {
    }

    public void shAre(View view)
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain"); 
        shareIntent.putExtra(Intent.EXTRA_TEXT, "try spycam ..........osm features.");
        startActivity(Intent.createChooser(shareIntent, "Share link using"));
        
    }

    public void contactUs(View view)
    {
    }
}
