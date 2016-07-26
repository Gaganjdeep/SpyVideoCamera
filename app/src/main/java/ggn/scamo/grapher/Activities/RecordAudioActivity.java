package ggn.scamo.grapher.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.PopupMenu;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import ggn.scamo.grapher.R;
import ggn.scamo.grapher.service_background.AudioRecorderService;
import ggn.scamo.grapher.utills.Background;
import ggn.scamo.grapher.utills.Gallery;
import ggn.scamo.grapher.utills.GlobalContstantG;

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
        Intent intent = new Intent(RecordAudioActivity.this, RecordBackGroundActivity.class);
        intent.putExtra("background", Background.AUDIO.getValue());
        startActivity(intent);
    }

    public void bySendingSMS(View view)
    {


        PopupMenu popup = new PopupMenu(RecordAudioActivity.this, view, Gravity.CENTER);

        SpannableString s = new SpannableString("ON");
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, s.length(), 0);
        popup.getMenu().add(s);

        SpannableString s2 = new SpannableString("OFF");
        s2.setSpan(new ForegroundColorSpan(Color.BLACK), 0, s2.length(), 0);
        popup.getMenu().add(s2);


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {

                if (item.getTitle().equals("ON"))
                {
                    getLocaldata().setMsgAudioRecording(true);
                }
                else
                {
                    getLocaldata().setMsgAudioRecording(false);
                }

                return false;
            }
        });
        popup.show();
    }

    public void byShowingPicture(View view)
    {
        Intent intnt = new Intent(RecordAudioActivity.this, ShowGalleryActivity.class);
        intnt.putExtra("gallery", Gallery.AUDIO_CLICK.getValue());
        startActivity(intnt);
    }


    public void byTakingPhoneNumber(View view)
    {
        serviceIntent = new Intent(RecordAudioActivity.this, AudioRecorderService.class);

        startService(serviceIntent);

        Intent i = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(i, GlobalContstantG.INSERT_CONTACT_REQUEST);
    }

    Intent serviceIntent;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == GlobalContstantG.INSERT_CONTACT_REQUEST)
        {
            if (resultCode == RESULT_OK)
            {

            }
            else if (resultCode == RESULT_CANCELED)
            {

            }

            if (serviceIntent != null)
            {
                stopService(serviceIntent);
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
