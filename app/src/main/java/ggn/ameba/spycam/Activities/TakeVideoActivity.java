package ggn.ameba.spycam.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SwitchCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import ggn.ameba.spycam.R;
import ggn.ameba.spycam.utills.CallBackG;
import ggn.ameba.spycam.utills.Camera;
import ggn.ameba.spycam.utills.Gallery;
import ggn.ameba.spycam.utills.GlobalContstantG;
import ggn.ameba.spycam.utills.UtillsG;

public class TakeVideoActivity extends BaseActivityG
{


    LinearLayout parentLayout;
    private TextView tvFolderName, tvSelectCamera;
    private SwitchCompat switchbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_image_video);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        findViewbyID();


//        showSomeGEffects();

    }


    private void findViewbyID()
    {
        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);

        tvFolderName = (TextView) findViewById(R.id.tvFolderName);

        ((LinearLayout) findViewById(R.id.layoutNumberOfImages)).setVisibility(View.GONE);

        tvSelectCamera = (TextView) findViewById(R.id.tvSelectCamera);

        tvFolderName.setText(getLocaldata().getFolderName());


        tvSelectCamera.setText(getLocaldata().isFrontCamVideo() ? "Front" : "Rear");


        switchbtn = (SwitchCompat) findViewById(R.id.switchbtn);
        switchbtn.setChecked(getLocaldata().isFlashOnVideo());
        switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                getLocaldata().setFlashOnVideo(b);
            }
        });
    }




/*
    private void showSomeGEffects()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            revealView.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    UtillsG.animateRevealColor(TakeVideoActivity.this, revealView, R.color.yellowish, new CallBackG()
                    {
                        @Override
                        public void callBack(Object response)
                        {
                            parentLayout.setVisibility(View.VISIBLE);
                            revealView.setVisibility(View.GONE);
                        }
                    });
                }
            }, 200);

        }
        else
        {
            parentLayout.setVisibility(View.VISIBLE);
            revealView.setVisibility(View.INVISIBLE);
        }


    }*/


    public void byShowingPicture(View view)
    {
        Intent intnt = new Intent(TakeVideoActivity.this, ShowGalleryActivity.class);
        intnt.putExtra("gallery", Gallery.IMAGE_VIDEO.getValue());
        startActivity(intnt);

    }

    public void byShowingVideo(View view)
    {
        Intent intnt = new Intent(TakeVideoActivity.this, ShowGalleryActivity.class);
        intnt.putExtra("gallery", Gallery.VIDEO_VIDEO.getValue());
        startActivity(intnt);
    }

    public void byTakingPhoneNumber(View view)
    {
    }

    public void SelectCamera(View view)
    {

        PopupMenu popup = new PopupMenu(TakeVideoActivity.this, tvSelectCamera);

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
                    getLocaldata().setFrontCamVideo(true);

                }
                else
                {
                    getLocaldata().setFrontCamVideo(false);

                }
                tvSelectCamera.setText(getLocaldata().isFrontCamVideo() ? "Front" : "Rear");

                return false;
            }
        });
        popup.show();
    }

    public void SelectNumberOfImages(View view)
    {
    }

    public void saveIn(View view)
    {
        UtillsG.inputDialog(TakeVideoActivity.this, "Enter folder name", new CallBackG<String>()
        {
            @Override
            public void callBack(String response)
            {
                getLocaldata().setFolderName(response);
                tvFolderName.setText(getLocaldata().getFolderName());
            }
        });
    }


}
