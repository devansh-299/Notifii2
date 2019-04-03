package com.piworks.notifii;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Timer timer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i1 = new Intent(MainActivity.this, screen2.class);
                startActivity(i1);
                finish();

            }
        },2000);

    }
}



/*
Print suggestions :
        Enter  should pass directly to next button

 */
