package ggn.brandcam.grapher.Activities;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;
import com.tiancaicc.springfloatingactionmenu.MenuItemView;
import com.tiancaicc.springfloatingactionmenu.OnMenuActionListener;
import com.tiancaicc.springfloatingactionmenu.SpringFloatingActionMenu;

import ggn.brandcam.grapher.R;
import ggn.brandcam.grapher.utills.UtillsG;

public class MenuActivity extends BaseActivityG implements View.OnClickListener
{

    private static int[] frameAnimRes = new int[]{
            R.drawable.ic_menu_camera,
            R.drawable.ic_video,
            R.drawable.ic_audio,
            R.drawable.ic_settings,
            R.drawable.ic_close

    };

    private SpringFloatingActionMenu springFloatingActionMenu;
    private int frameDuration = 20;
    private AnimationDrawable frameAnim;
    private AnimationDrawable frameReverseAnim;

    private View revealView;


    public static MenuActivity menuActivity;

    TextView tvHelp;


    private CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        UtillsG.changeLanguage(MenuActivity.this, getLocaldata().getlanguage());
        setContentView(R.layout.activity_help);
        menuActivity = this;


        tvHelp = (TextView) findViewById(R.id.tvHelp);

        createFabFrameAnim();
        createFabReverseFrameAnim();


        final FloatingActionButton fab = new FloatingActionButton(this);
        fab.setType(FloatingActionButton.TYPE_NORMAL);
        fab.setImageDrawable(frameAnim);
//        fab.setImageResource(android.R.drawable.ic_dialog_email);
        fab.setColorPressedResId(R.color.colorPrimary);
        fab.setColorNormalResId(R.color.yellowish);
        fab.setColorRippleResId(R.color.camerag);
        fab.setShadow(true);
        springFloatingActionMenu = new SpringFloatingActionMenu.Builder(this)
                .fab(fab)
                .addMenuItem(R.color.camerag, R.drawable.ic_menu_camera, "Photo", R.color.white, this)
                .addMenuItem(R.color.videog, R.drawable.ic_video, "Video", R.color.white, this)
                .addMenuItem(R.color.audiog, R.drawable.ic_audio, "Audio", R.color.white, this)
                .addMenuItem(R.color.settingsg, R.drawable.ic_settings, "Settings", R.color.white, this)
//                .addMenuItem(R.color.helpg, R.drawable.ic_help, "help", R.color.white, this)


                .animationType(SpringFloatingActionMenu.ANIMATION_TYPE_TUMBLR)
                .revealColor(R.color.colorPrimary)
                .gravity(Gravity.RIGHT | Gravity.BOTTOM)
                .onMenuActionListner(new OnMenuActionListener()
                {
                    @Override
                    public void onMenuOpen()
                    {

                        if (countDownTimer != null)
                        {
                            countDownTimer.cancel();
                        }


                        fab.setImageDrawable(frameAnim);
                        frameReverseAnim.stop();
                        frameAnim.start();


                    }

                    @Override
                    public void onMenuClose()
                    {
                        fab.setImageDrawable(frameReverseAnim);
                        frameAnim.stop();
                        frameReverseAnim.start();
                    }
                })
                .build();


        revealView = springFloatingActionMenu;


        countDownTimer = new CountDownTimer(2200, 2200)
        {

            @Override
            public void onTick(long l)
            {

            }

            @Override
            public void onFinish()
            {

                springFloatingActionMenu.showMenu();

            }
        };


        countDownTimer.start();

    }

    private void createFabFrameAnim()
    {
        frameAnim = new AnimationDrawable();
        frameAnim.setOneShot(true);
        Resources resources = getResources();
        for (int res : frameAnimRes)
        {
            frameAnim.addFrame(resources.getDrawable(res), frameDuration);
        }
    }

    private void createFabReverseFrameAnim()
    {
        frameReverseAnim = new AnimationDrawable();
        frameReverseAnim.setOneShot(true);
        Resources resources = getResources();
        for (int i = frameAnimRes.length - 1; i >= 0; i--)
        {
            frameReverseAnim.addFrame(resources.getDrawable(frameAnimRes[i]), frameDuration);
        }
    }

    @Override
    public void onBackPressed()
    {
        if (springFloatingActionMenu.isMenuOpen())
        {
            springFloatingActionMenu.hideMenu();
        }
        else
        {
            super.onBackPressed();
        }
    }


    final String PHOTO    = "Photo";
    final String VIDEO    = "Video";
    final String SETTINGS = "Settings";
    final String AUDIO    = "Audio";


    @Override
    public void onClick(View v)
    {
        MenuItemView menuItemView = (MenuItemView) v;


        tvHelp.setVisibility(View.INVISIBLE);

        switch (menuItemView.getLabelTextView().getText().toString())
        {


            case PHOTO:

                takeImage(v);

                break;


            case VIDEO:

                recordVideo(v);
                break;


            case SETTINGS:


                seTTings(v);


                break;


            case AUDIO:

                audioRecorder(v);

                break;


        }

//        Toast.makeText(this, menuItemView.getLabelTextView().getText(), Toast.LENGTH_SHORT).show();
    }


    public void takeImage(View view)
    {

        Intent intent = new Intent(MenuActivity.this, TakeImageActivity.class);

        animateRevealColor(view, intent);
    }

    public void recordVideo(View view)
    {
        Intent intent = new Intent(MenuActivity.this, TakeVideoActivity.class);

        animateRevealColor(view, intent);
    }

    public void audioRecorder(View view)
    {
        Intent intent = new Intent(MenuActivity.this, RecordAudioActivity.class);
        animateRevealColor(view, intent);
    }

    public void seTTings(View view)
    {
        Intent intent = new Intent(MenuActivity.this, SettingsActivity.class);
        animateRevealColor(view, intent);
    }


    int[] values;

    private void animateRevealColor(View viewRoot, Intent intent)
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            values = new int[2];
            viewRoot.getLocationOnScreen(values);

//        Log.wtf("X & Y", values[0] + " " + values[1]);

            revealRewind(intent);
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

    }


    private void animateRevealColorFromCoordinates(final View viewRoot, int x, int y)
    {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        final Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);

        viewRoot.setVisibility(View.VISIBLE);

        anim.setDuration(800);
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
                tvHelp.setVisibility(View.VISIBLE);
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

    private void revealRewind(final Intent intnt)
    {

        if (values == null || Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
        {
            return;
        }


        int x = values[0] + 80;
        int y = values[1] + 50;


        float finalRadius = (float) Math.hypot(revealView.getWidth(), revealView.getHeight());

        final Animator anim = ViewAnimationUtils.createCircularReveal(revealView, x, y, finalRadius, 0);


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
                startActivityG(intnt);
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            if (revealView.getVisibility() == View.INVISIBLE)
            {
                animateRevealColorFromCoordinates(revealView, values[0] + 80, values[1] + 50);
            }
        }
        else
        {
            tvHelp.setVisibility(View.VISIBLE);
        }

        super.onResume();
    }


}