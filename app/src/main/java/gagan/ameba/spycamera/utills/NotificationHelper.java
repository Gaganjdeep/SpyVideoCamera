package gagan.ameba.spycamera.utills;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;

import java.io.IOException;

import ggn.scamo.grapher.R;

/**
 * Created by gagandeep on 28 Mar 2016.
 */
public class NotificationHelper
{


    public NotificationHelper(Context context, Uri ImageUri)
    {
        showNotification(context, ImageUri);

    }

    private void showNotification(Context context, Uri imagUri)
    {
//        Intent notificationIntent = new Intent(context, MainActivityG.class);
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);


        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                "content://media/internal/images/media"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setSmallIcon(R.mipmap.ic_launcher);

        builder.setContentIntent(pendingIntent);

        builder.setAutoCancel(true);

//        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.no_img));

        try
        {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imagUri);
//        }
            builder.setLargeIcon(bitmap);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        builder.setContentTitle("SpyCam");
        builder.setContentText("Image Captured ! Click to open!");


        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        if (defaultSound == null)
        {
            defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            if (defaultSound == null)
            {
                defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            }
        }

        builder.setSound(defaultSound);


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, builder.build());
    }
}
