package ggn.ameba.spycam.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ggn.ameba.spycam.utills.SharedPrefHelper;

/**
 * Created by gagandeep on 24 May 2016.
 */
public class BaseActivityG extends AppCompatActivity
{

    SharedPrefHelper localdata;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        localdata = new SharedPrefHelper(getApplicationContext());

        super.onCreate(savedInstanceState);
    }


    public SharedPrefHelper getLocaldata()
    {
        return localdata;
    }


/*    Dialog dialog;

    public void showProgress() {
        dialog = new Dialog(this, R.style.Theme_Dialog);
        dialog.setContentView(R.layout.progress_dialog);
        dialog.setCancelable(false);
        dialog.show();

    }

    public void cancelProgress() {
        if (dialog != null) {
            dialog.cancel();
        }
    }*/
}
