package com.piworks.notifii;


import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;


public class settings extends AppCompatActivity {
    CheckBox checkBox;
    String vibrateString;
    private static SharedPreferences mpreferences;
    private  String sharedPrefFile = "com.piworks.devansh.sharedpreferencesfile";
    static String vibrateStringfinal = "false";
    //Context ctx = getApplicationContext();
   // static  String darkmodecheck = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mpreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
       // darkmode = (CheckBox)findViewById(R.id.darkmode);


        // for getting from sharedpreferences

        vibrateStringfinal = mpreferences.getString("for vibration","false");
        checkBox.setChecked(Boolean.parseBoolean(vibrateStringfinal));


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    // your code to checked checkbox
                    vibrateString = "true";

                }
                else {
                    vibrateString = "false";
                }
            }

    });
/*
        darkmode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    // your code to checked checkbox
                    layout.setBackground(ContextCompat.getDrawable(ctx, R.drawable.black_background));

                }
                else {
                    layout.setBackground(ContextCompat.getDrawable(ctx, R.drawable.background));
                }
            }

        });

*/


    }


    @Override
    public void onBackPressed() {
        SharedPreferences.Editor preferenceseditor = mpreferences.edit();
        preferenceseditor.putString("for vibration",vibrateString);
        preferenceseditor.apply();
        Intent  settingtoscreen2 = new Intent(this ,screen2.class);
        startActivity(settingtoscreen2);
    }

    @Override
    protected void onResume() {

        super.onResume();
        vibrateStringfinal = mpreferences.getString("for vibration","false");
        checkBox.setChecked(Boolean.parseBoolean(vibrateStringfinal));
    }

    @Override
    protected  void onPause() {
    super.onPause();
    SharedPreferences.Editor preferenceseditor = mpreferences.edit();
    preferenceseditor.putString("for vibration",vibrateString);
    preferenceseditor.apply();
}
}



