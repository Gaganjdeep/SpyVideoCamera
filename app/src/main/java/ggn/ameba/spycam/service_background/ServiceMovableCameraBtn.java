//package ggn.ameba.spycam.service_background;
//
//import android.annotation.TargetApi;
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.PixelFormat;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Handler;
//import android.os.IBinder;
//import android.view.GestureDetector;
//import android.view.Gravity;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.Toast;
//
//import ggn.ameba.spycam.Activities.MainActivityG;
//import ggn.ameba.spycam.R;
//import ggn.ameba.spycam.utills.SharedPrefHelper;
//
//
//public class ServiceMovableCameraBtn extends Service implements View.OnTouchListener
//{
//    private WindowManager windowManager;
//
//    boolean mHasDoubleClicked = false;
//    long lastPressTime;
//
//    WindowManager.LayoutParams container_params;
//
//    private int   initialX;
//    private int   initialY;
//    private float initialTouchX;
//    private float initialTouchY;
//
//    Context con;
//
//    android.support.design.widget.FloatingActionButton btnCapture;
//
//    private GestureDetector gestureDetector;
//
//    SharedPrefHelper sharedPrefHelper;
//
//    @Override
//    public IBinder onBind(Intent intent)
//    {
//        return null;
//    }
//
//    @Override
//    public void onCreate()
//    {
//        super.onCreate();
//        con = this;
//        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
//        setTheme(R.style.AppTheme);
//
//        gestureDetector = new GestureDetector(this, new SingleTapConfirm());
//        sharedPrefHelper = new SharedPrefHelper(this);
//
//        //  LAYOUT PARAMS
//        container_params = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
//
//        btnCapture = new android.support.design.widget.FloatingActionButton(this);
//        btnCapture.setImageResource(R.mipmap.ic_launcher);
//
//        btnCapture.setOnTouchListener(this);
////        btnCapture.setOnClickListener(this);
//
//
//        windowManager.addView(btnCapture, container_params);
//    }
//
//    @Override
//    public void onDestroy()
//    {
//        super.onDestroy();
//        if (btnCapture != null)
//        {
//            windowManager.removeView(btnCapture);
//        }
//    }
//
//    Toast toast;
//
//    @Override
//    public boolean onTouch(View view, MotionEvent event)
//    {
//        WindowManager.LayoutParams paramsF = container_params;
//
//        if (gestureDetector.onTouchEvent(event))
//        {
//            // single tap
//            onSingleClick();
//            return true;
//        }
//        else
//        {
//
//            switch (event.getAction())
//            {
//                case MotionEvent.ACTION_DOWN:
//                    long pressTime = System.currentTimeMillis();
//
//                    if (pressTime - lastPressTime <= 300)
//                    {
//                        generateNotification();
//                        mHasDoubleClicked = true;
//                    }
//                    else
//                    {
//                        mHasDoubleClicked = false;
//                    }
//                    lastPressTime = pressTime;
//                    initialX = paramsF.x;
//                    initialY = paramsF.y;
//                    initialTouchX = event.getRawX();
//                    initialTouchY = event.getRawY();
//                    break;
//                case MotionEvent.ACTION_UP:
//
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    paramsF.x = initialX + (int) (event.getRawX() - initialTouchX);
//                    paramsF.y = initialY + (int) (event.getRawY() - initialTouchY);
//                    windowManager.updateViewLayout(btnCapture, paramsF);
//                    break;
//            }
//
//        }
//        return false;
//    }
//
//    void capture_image()
//    {
//        Intent front_translucent = new Intent(ServiceMovableCameraBtn.this, CamerService.class);
//        front_translucent.putExtra("Front_Request", sharedPrefHelper.isFrontCam());
//        front_translucent.putExtra("Quality_Mode", 0);
//
//        if (!sharedPrefHelper.isFrontCam())
//        {
//            if (sharedPrefHelper.isFlashOn())
//            {
//                front_translucent.putExtra("FLASH", "auto");
//            }
//            else
//            {
//                front_translucent.putExtra("FLASH", "off");
//            }
//
//
//        }
//
//
//        startService(
//                front_translucent);
//    }
//
//
//    public void showToast(final String text)
//    {
//        new Handler().post(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                if (toast != null)
//                {
//                    toast.cancel();
//                }
//                toast = Toast.makeText(ServiceMovableCameraBtn.this, text, Toast.LENGTH_LONG);
//                toast.setGravity(Gravity.CENTER, 0, 0);
//                toast.show();
//            }
//        });
//
//
//    }
//
//
//    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener
//    {
//
//        @Override
//        public boolean onSingleTapConfirmed(MotionEvent event)
//        {
//            return true;
//        }
//
//        @Override
//        public boolean onSingleTapUp(MotionEvent e)
//        {
//            return true;
//        }
//    }
//
//
//    public void onSingleClick()
//    {
//
//        capture_image();
//
//
//        try
//        {
//            showToast("Say Cheese..!");
//           /* new CountDownTimer(400, 100)
//            {
//
//
//                int count = 0;
//
//                @Override
//                public void onTick(long l)
//                {
//                    count++;
//
//                }
//
//                @Override
//                public void onFinish()
//                {
//
//                }
//            }.start();*/
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    private void generateNotification()
//    {
//        ServiceMovableCameraBtn.this.stopSelf();
//
//        Context context            = ServiceMovableCameraBtn.this;
//        String  message            = "Tap to open SpyCam.";
//        Intent  notificationIntent = new Intent(ServiceMovableCameraBtn.this, MainActivityG.class);
//
//        int icon = R.mipmap.ic_launcher;
//        try
//        {
//
//
//            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//
//            String title = "SpyCam";
//            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            PendingIntent intent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//            Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            if (defaultSound == null)
//            {
//                defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
//                if (defaultSound == null)
//                {
//                    defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//                }
//            }
//            Notification.Builder builder = new Notification.Builder(context).setContentTitle(title).setContentText(message).setContentIntent(intent).setSmallIcon(icon).setLights(Color.GREEN, 500, 600).setAutoCancel(true).setSound(defaultSound);
//
//            builder.setOngoing(true);
//
//            Notification not = new Notification.BigTextStyle(builder).bigText(message).build();
//            if (defaultSound == null)
//            {
//                not.defaults |= Notification.DEFAULT_SOUND;
//            }
//
//
//            notificationManager.notify(1, not);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//
//}