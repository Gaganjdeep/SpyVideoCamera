package ggn.ameba.spycam.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import ggn.ameba.spycam.R;
import ggn.ameba.spycam.utills.UtillsG;

import static ggn.ameba.spycam.Activities.SettingsActivity.LANGUAGE.ENGLISH;
import static ggn.ameba.spycam.Activities.SettingsActivity.LANGUAGE.FRENCH;
import static ggn.ameba.spycam.Activities.SettingsActivity.LANGUAGE.GERMAN;

public class SettingsActivity extends BaseActivityG
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        UtillsG.changeLanguage(SettingsActivity.this, getLocaldata().getlanguage());
        setContentView(R.layout.activity_settings);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    public enum LANGUAGE
    {
        ENGLISH("en"),
        FRENCH("fr"),
        GERMAN("de");

        String language;

        LANGUAGE(String en)
        {
            language = en;
        }

        public String getLanguage()
        {
            return language;
        }
    }


    public void chooseLanguage(View view)
    {
        PopupMenu popup = new PopupMenu(SettingsActivity.this, view);
        popup.setGravity(Gravity.RIGHT);

        SpannableString sEnglish = new SpannableString(ENGLISH.toString());
        sEnglish.setSpan(new ForegroundColorSpan(Color.BLACK), 0, sEnglish.length(), 0);
        popup.getMenu().add(sEnglish);

        SpannableString sFrench = new SpannableString(LANGUAGE.FRENCH.toString());
        sFrench.setSpan(new ForegroundColorSpan(Color.BLACK), 0, sFrench.length(), 0);
        popup.getMenu().add(sFrench);


        SpannableString sGerman = new SpannableString(LANGUAGE.GERMAN.toString());
        sGerman.setSpan(new ForegroundColorSpan(Color.BLACK), 0, sGerman.length(), 0);
        popup.getMenu().add(sGerman);


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getTitle().toString())
                {
                    case "ENGLISH":

                        getLocaldata().setLanguage(ENGLISH.getLanguage());

                        UtillsG.changeLanguage(SettingsActivity.this, ENGLISH.getLanguage());


                        break;
                    case "FRENCH":

                        getLocaldata().setLanguage(FRENCH.getLanguage());
                        UtillsG.changeLanguage(SettingsActivity.this, FRENCH.getLanguage());

                        break;
                    case "GERMAN":

                        getLocaldata().setLanguage(GERMAN.getLanguage());
                        UtillsG.changeLanguage(SettingsActivity.this, GERMAN.getLanguage());


                        break;
                }


                UtillsG.showToast("Language changed.", SettingsActivity.this, true);

//                Intent refresh = new Intent(SettingsActivity.this, SplashActivity.class);
//                startActivity(refresh);
//                finish();
                recreate();

                if (MainActivity.mainActivity != null)
                    MainActivity.mainActivity.recreate();


                return false;
            }
        });
        popup.show();


    }

    public void comMent(View view)
    {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://play.google.com/store/apps/details?id=ggn.ameba.spycam"));
        startActivity(i);
    }

    public void inFo(View view)
    {
        Intent refresh = new Intent(SettingsActivity.this, InfoActivity.class);
        startActivity(refresh);
    }

    public void shAre(View view)
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=ggn.ameba.spycam");
        startActivity(Intent.createChooser(shareIntent, "Share link using"));

    }

    public void contactUs(View view)
    {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "WS90785@gmail.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Spy on sexy girls");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
//emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text

        startActivity(Intent.createChooser(emailIntent, "Send mail using"));
    }
}
