package com.blueeagle.supportedmulti_languageapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnChangeLanguages, btnOpenAct;
    private int languages;

    public final String EN = "en";
    public final String VI = "vi";
    public final String JA = "ja";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get current language
        languages = getCurrentLanguage();

        btnChangeLanguages = (Button) findViewById(R.id.btnChangeLanguages);
        btnChangeLanguages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languages = (languages + 1) % 3;
                Log.d("ABC", languages + "");
                // Save language setting
                saveLang(languages);
                // Change languages
                changeLanguages(languages);
            }

            private void changeLanguages(int languages) {
                if (languages == 0)
                    setLocale(EN);
                else if (languages == 1)
                    setLocale(VI);
                else
                    setLocale(JA);
            }
        });

        btnOpenAct = (Button) findViewById(R.id.btnOpenActivity);
        btnOpenAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    public int getCurrentLanguage() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        return sp.getInt("lang", 0);
    }

    public void saveLang(int lang) {
        SharedPreferences.Editor editor =
                PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putInt("lang", lang);
        editor.apply();
    }

    // Set locale
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();

        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

        Toast.makeText(getApplicationContext(), "Change your languages to " + lang, Toast.LENGTH_SHORT).show();
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }
}
