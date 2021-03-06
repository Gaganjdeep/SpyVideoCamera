package ggn.brandcam.grapher.Activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SwitchCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import ggn.brandcam.grapher.R;
import ggn.brandcam.grapher.service_background.CamerService;
import ggn.brandcam.grapher.utills.Background;
import ggn.brandcam.grapher.utills.Camera;
import ggn.brandcam.grapher.utills.Gallery;
import ggn.brandcam.grapher.utills.GlobalContstantG;
import ggn.brandcam.grapher.utills.ReceiverCamera;

public class TakeImageActivity extends BaseActivityG
{

    LinearLayout parentLayout;

    private TextView  tvImageCount, tvSelectCamera;


    private SwitchCompat switchbtn;


    ReceiverCamera receiverCamera;

    @Override
    protected void onDestroy()
    {
        unregisterReceiver(receiverCamera);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_image_video);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


        receiverCamera = new ReceiverCamera();

        registerReceiver(receiverCamera, new IntentFilter(ReceiverCamera.CLICK_IMAGE));


        findViewbyID();


//        showSomeGEffects();
    }

    private LinearLayout layoutNumberOfImages;

    private void findViewbyID()
    {
        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);


        tvImageCount = (TextView) findViewById(R.id.tvImageCount);
        tvSelectCamera = (TextView) findViewById(R.id.tvSelectCamera);


        tvImageCount.setText(getLocaldata().getNumberOfImages() + "");
        tvSelectCamera.setText(getLocaldata().isFrontCamCamera() ? "Front" : "Rear");


        layoutNumberOfImages = (LinearLayout) findViewById(R.id.layoutNumberOfImages);


        switchbtn = (SwitchCompat) findViewById(R.id.switchbtn);
        switchbtn.setChecked(getLocaldata().isFlashOnCamera());

        switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                getLocaldata().setFlashOnCamera(b);
            }
        });

    }


    public void byShowingPicture(View view)
    {
        Intent intnt = new Intent(TakeImageActivity.this, ShowGalleryActivity.class);
        intnt.putExtra("gallery", Gallery.IMAGE_CLICK.getValue());
        startActivity(intnt);
    }

    public void byShowingVideo(View view)
    {
        Intent intnt = new Intent(TakeImageActivity.this, ShowGalleryActivity.class);
        intnt.putExtra("gallery", Gallery.VIDEO_CLICK.getValue());
        startActivity(intnt);
    }

    public void byTakingPhoneNumber(View view)
    {
        CamerService.count = 0;

        serviceIntent = getBackgroundIntent(CamerService.class);

        startService(serviceIntent);

        Intent i = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(i, GlobalContstantG.INSERT_CONTACT_REQUEST);
    }

    Intent serviceIntent;


    public void SelectCamera(View view)
    {
        PopupMenu popup = new PopupMenu(TakeImageActivity.this, tvSelectCamera);

        SpannableString s = new SpannableString(Camera.FRONT.toString());
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, s.length(), 0);
        popup.getMenu().add(s);

        SpannableString sHome = new SpannableString(Camera.REAR.toString());
        sHome.setSpan(new ForegroundColorSpan(Color.BLACK), 0, sHome.length(), 0);
        popup.getMenu().add(sHome);


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                if (item.getTitle().toString().equals(Camera.FRONT.toString()))
                {
                    getLocaldata().setFrontCamCamera(true);
                }
                else
                {
                    getLocaldata().setFrontCamCamera(false);
                }

                tvSelectCamera.setText(getLocaldata().isFrontCamCamera() ? "Front" : "Rear");

                return false;
            }
        });
        popup.show();
    }

    int imagesCount[] = {1,2, 5, 8, 10};

    public void SelectNumberOfImages(View view)
    {

        PopupMenu popup = new PopupMenu(TakeImageActivity.this, tvImageCount, Gravity.CENTER);


        for (int i = 0; i < imagesCount.length; i++)
        {
            SpannableString s = new SpannableString(imagesCount[i] + "");
            s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, s.length(), 0);
            popup.getMenu().add(s);
        }


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                getLocaldata().setNumberofImages(Integer.parseInt(item.getTitle().toString()));
                tvImageCount.setText(getLocaldata().getNumberOfImages() + "");
                return false;
            }
        });
        popup.show();

    }




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

    public void runInBackground(View view)
    {
        Intent intent = new Intent(TakeImageActivity.this, RecordBackGroundActivity.class);
        intent.putExtra("background", Background.IMAGE.getValue());
        startActivity(intent);
    }
}
