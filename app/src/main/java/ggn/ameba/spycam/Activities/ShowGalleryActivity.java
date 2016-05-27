package ggn.ameba.spycam.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import ggn.ameba.spycam.CustomGallery.GalleryActivites.GalleryFragment;
import ggn.ameba.spycam.CustomGallery.GalleryActivites.VideoFragment;
import ggn.ameba.spycam.R;
import ggn.ameba.spycam.utills.Gallery;

public class ShowGalleryActivity extends BaseActivityG
{


    private String title;
    private int    theme;
    private Fragment fragment = null;

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


                break;

            case VIDEO_CLICK:

                fragment = new VideoFragment();
                title = "Videos";


                theme = R.style.AppThemeCamera;

                break;

            case IMAGE_VIDEO:

                fragment = new GalleryFragment();
                title = "Images";

                theme = R.style.AppThemeVideo;


                break;


            case VIDEO_VIDEO:

                fragment = new VideoFragment();
                title = "Videos";

                theme = R.style.AppThemeVideo;


                break;

        }

        setTheme(theme);


        setContentView(R.layout.activity_show_gallery);


        getLocaldata().setCurrentTheme(theme);


        settingActionBar(title);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();


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
