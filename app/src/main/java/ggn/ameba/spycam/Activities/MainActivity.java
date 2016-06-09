package ggn.ameba.spycam.Activities;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import ggn.ameba.spycam.R;
import ggn.ameba.spycam.utills.SharedPrefHelper;
import ggn.ameba.spycam.utills.UtillsG;

public class MainActivity extends AppCompatActivity
{

    private View revealView;


    public static MainActivity mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        UtillsG.changeLanguage(MainActivity.this, (new SharedPrefHelper(MainActivity.this)).getlanguage());
        setContentView(R.layout.activity_main);

//        TableLayout tablelayout = (TableLayout) findViewById(R.id.tablelayout);
        mainActivity=this;
        revealView = (View) findViewById(R.id.revealView);


    }

    int[] values;

    private void animateRevealColor(View viewRoot, @ColorRes int color, Intent intent)
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            values = new int[2];
            viewRoot.getLocationOnScreen(values);

            int cx = values[0] + 100;
            int cy = values[1] + 60;
//        Log.wtf("X & Y", values[0] + " " + values[1]);
            animateRevealColorFromCoordinates(revealView, color, cx, cy, intent);
        }
        else
        {
            startActivityG(intent);
        }


    }


    private void startActivityG(Intent intent)
    {
        if (intent != null)
        {
            startActivity(intent);
        }
        else
        {
            revealView.setVisibility(View.INVISIBLE);

        }
    }


    private void animateRevealColorFromCoordinates(final View viewRoot, @ColorRes int color, int x, int y, final Intent intent)
    {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        final Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);

        viewRoot.setBackgroundColor(ContextCompat.getColor(this, color));
        viewRoot.setVisibility(View.VISIBLE);

        anim.setDuration(700);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();


        anim.addListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animator)
            {

            }

            @Override
            public void onAnimationEnd(Animator animator)
            {
                startActivityG(intent);
            }

            @Override
            public void onAnimationCancel(Animator animator)
            {

            }

            @Override
            public void onAnimationRepeat(Animator animator)
            {

            }
        });

    }

    private void revealRewind()
    {

        if (values == null || Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
        {
            return;
        }


        int x = values[0] + 100;
        int y = values[1] + 60;


        float finalRadius = (float) Math.hypot(revealView.getWidth(), revealView.getHeight());

        final Animator anim = ViewAnimationUtils.createCircularReveal(revealView, x, y, finalRadius, 0);

        revealView.setVisibility(View.VISIBLE);

        anim.setDuration(700);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();


        anim.addListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animator)
            {

            }

            @Override
            public void onAnimationEnd(Animator animator)
            {
                revealView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator)
            {

            }

            @Override
            public void onAnimationRepeat(Animator animator)
            {

            }
        });

    }


    @Override
    protected void onResume()
    {

        if (revealView.getVisibility() == View.VISIBLE)
        {
            revealRewind();
        }

        super.onResume();
    }

    public void takeImage(View view)
    {

        Intent intent = new Intent(MainActivity.this, TakeImageActivity.class);

        animateRevealColor(view, R.color.ligtblue, intent);
    }

    public void recordVideo(View view)
    {
        Intent intent = new Intent(MainActivity.this, TakeVideoActivity.class);

        animateRevealColor(view, R.color.yellowish, intent);
    }

    public void audioRecorder(View view)
    {
        Intent intent = new Intent(MainActivity.this, RecordAudioActivity.class);
        animateRevealColor(view, R.color.redish, intent);
    }

    public void getMoreApp(View view)
    {
//        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        animateRevealColor(view, R.color.bluish, null);


        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://play.google.com/store/apps/developer?id=Valerie+Evans"));
        startActivity(i);

    }

    public void seTTings(View view)
    {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        animateRevealColor(view, R.color.grenish, intent);
    }

    public void heLp(View view)
    {
        Intent intent = new Intent(MainActivity.this, HelpActivity.class);
        animateRevealColor(view, R.color.yellowlast, intent);
    }
}
