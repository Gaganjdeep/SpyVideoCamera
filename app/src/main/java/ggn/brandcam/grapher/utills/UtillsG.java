package ggn.brandcam.grapher.utills;

import android.animation.Animator;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import ggn.brandcam.grapher.R;

/**
 * Created by gagandeep on 24 May 2016.
 */
public class UtillsG
{


    public static void animateRevealColor(Context context, View viewRoot, @ColorRes int color, CallBackG callBackG)
    {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        animateRevealColorFromCoordinates(context, viewRoot, color, cx, cy, callBackG);
    }

    public static Animator animateRevealColorFromCoordinates(Context context, final View viewRoot, @ColorRes int color, int x, int y, final CallBackG callBackG)
    {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
        viewRoot.setBackgroundColor(ContextCompat.getColor(context, color));
        anim.setDuration(600);
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
                callBackG.callBack(null);
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

        return anim;
    }


    public static Intent gIntent(Activity activity, Class target, View view, String transitionName)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Intent i = new Intent(activity, target);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity,
                    Pair.create(view, transitionName));

            return i;

//            activity.startActivity(i, options.toBundle());

        }
        else
        {
            return new Intent(activity, target);
        }
    }


    //dialog onw button
    public static Dialog global_dialog;

    public static void inputDialog(final Context con, String hint, final CallBackG<String> callBackG)
    {
        global_dialog = new Dialog(con, R.style.Theme_Dialog);
        global_dialog.setContentView(R.layout.input_dialog);
        global_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        global_dialog.setCanceledOnTouchOutside(true);

        final EditText edText = (EditText) global_dialog.findViewById(R.id.edText);
        Button         ok     = (Button) global_dialog.findViewById(R.id.ok);

        edText.setText(hint);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(global_dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        global_dialog.show();
        global_dialog.getWindow().setAttributes(lp);


        ok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (edText.getText().toString().trim().isEmpty())
                {
//                    edText.setError("Enter some name.");
                }
                else
                {
                    callBackG.callBack(edText.getText().toString());
                    global_dialog.dismiss();
                }
            }
        });
    }


    public static boolean isMyServiceRunning(Class<?> serviceClass, Context context)
    {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE))
        {
            if (serviceClass.getName().equals(service.service.getClassName()))
            {
                return true;
            }
        }
        return false;
    }


    public static Toast toastG;


    public static void showToast(String msg, Context context, boolean center)
    {
        if (toastG != null)
        {
            toastG.cancel();
        }
        toastG = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        if (center)
        {
            toastG.setGravity(Gravity.CENTER, 0, 0);
        }

        toastG.show();

    }



    public static void changeLanguage(Context context, String lang)
    {
//        Locale         myLocale = new Locale(lang);
//        Resources      res      = context.getResources();
//        DisplayMetrics dm       = res.getDisplayMetrics();
//        Configuration  conf     = res.getConfiguration();
//        conf.locale = myLocale;
//        res.updateConfiguration(conf, dm);

//
//        Locale locale = new Locale(lang);
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        context.getResources().updateConfiguration(config,
//                context.getResources().getDisplayMetrics());
        Resources res = context.getResources();
        // Change locale settings in the app.
        DisplayMetrics                    dm   = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(lang.toLowerCase());
        res.updateConfiguration(conf, dm);

    }


}
