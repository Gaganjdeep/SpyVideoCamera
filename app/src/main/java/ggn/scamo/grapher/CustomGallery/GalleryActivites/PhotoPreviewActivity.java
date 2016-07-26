package ggn.scamo.grapher.CustomGallery.GalleryActivites;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ggn.scamo.grapher.Activities.BaseActivityG;
import ggn.scamo.grapher.CustomGallery.component.PhoneMediaControl;
import ggn.scamo.grapher.CustomGallery.component.PhotoPreview;
import ggn.scamo.grapher.R;

public class PhotoPreviewActivity extends BaseActivityG implements ViewPager.OnPageChangeListener
{

    private   ViewPager                          mViewPager;
    protected List<PhoneMediaControl.PhotoEntry> photos;
    protected int                                current, folderPosition;

    protected Context context;
    private   Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTheme(getLocaldata().getCurrentTheme());
        setContentView(R.layout.activity_photo_preview);


        context = PhotoPreviewActivity.this;
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle mBundle = getIntent().getExtras();
        folderPosition = mBundle.getInt("Key_FolderID");
        current = mBundle.getInt("Key_ID");

        photos = GalleryFragment.albumsSorted.get(folderPosition).photos;

        mViewPager = (ViewPager) findViewById(R.id.vp_base_app);
        mViewPager.setOnPageChangeListener(this);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        bindData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    protected void bindData()
    {
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(current);
        toolbar.setTitle((current + 1) + "/" + photos.size());
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter()
    {

        @Override
        public int getCount()
        {
            if (photos == null)
            {
                return 0;
            }
            else
            {
                return photos.size();
            }
        }

        @Override
        public View instantiateItem(final ViewGroup container, final int position)
        {
            PhotoPreview photoPreview = new PhotoPreview(context);
            ((ViewPager) container).addView(photoPreview);
            photoPreview.loadImage(photos.get(position));
            return photoPreview;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }

    };

    @Override
    public void onPageScrollStateChanged(int arg0)
    {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2)
    {

    }

    @Override
    public void onPageSelected(int arg0)
    {
        current = arg0;
        updatePercent();
    }

    protected void updatePercent()
    {
        toolbar.setTitle((current + 1) + "/" + photos.size());
    }
}
